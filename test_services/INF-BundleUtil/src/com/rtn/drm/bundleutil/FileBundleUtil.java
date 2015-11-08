package com.rtn.drm.bundleutil;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class FileBundleUtil
{

	public static List<BundleDescriptor> getBundles(String pathName)
	{
		LinkedList<BundleDescriptor> descriptors = new LinkedList<BundleDescriptor>();
		File path = new File(pathName);
		String[] files = path.list(new FilenameFilter()
		{
			@Override
			public boolean accept(File directory, String filename)
			{
				return filename.endsWith(".jar");
			}

		});
		if (files != null)
		{
			for (int i = 0; i < files.length; i++)
			{
				File f = new File(pathName + "/" + files[i]);
				if (f.exists() && f.canRead())
				{
					try
					{
						JarFile jarFile = new JarFile(f);
						JarEntry jarEntry = jarFile.getJarEntry("META-INF/MANIFEST.MF");
						if (jarEntry != null)
						{
							InputStream stream = jarFile.getInputStream(jarEntry);
							if (stream != null)
							{
								Manifest manifest = new Manifest(stream);
								BundleDescriptor descriptor = new BundleDescriptor();
								descriptor.setBundleName(manifest.getMainAttributes().getValue(
								      "Bundle-Name"));
								descriptor.setBundleSymbolicName(manifest.getMainAttributes().getValue(
								      "Bundle-SymbolicName"));
								descriptor.setDescription(manifest.getMainAttributes().getValue(
								      "Bundle-Description"));
								descriptor.setVersion(manifest.getMainAttributes().getValue(
								      "Bundle-Version"));
								descriptor.setImports(manifest.getMainAttributes().getValue(
								      "Import-Package"));
								descriptor.setExports(manifest.getMainAttributes().getValue(
								      "Export-Package"));
								descriptors.add(descriptor);

							}
						}
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
		return descriptors;
	}

	public static void main(String args[])
	{
		for (BundleDescriptor desc : FileBundleUtil.getBundles("./jars/drm"))
		{
			System.out.println(desc);
		}

	}
}
