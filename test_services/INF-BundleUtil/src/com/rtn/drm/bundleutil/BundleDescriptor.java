package com.rtn.drm.bundleutil;

public class BundleDescriptor
{
	private String bundleName;
	private String bundleSymbolicName;
	private String description;
	private String longDesription;
	private String version;
	private String imports;
	private String exports;
	private String uri;
	public String getBundleName()
	{
		return bundleName;
	}
	public void setBundleName(String bundleName)
	{
		this.bundleName = bundleName;
	}
	public String getBundleSymbolicName()
	{
		return bundleSymbolicName;
	}
	public void setBundleSymbolicName(String bundleSymbolicName)
	{
		this.bundleSymbolicName = bundleSymbolicName;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getLongDesription()
	{
		return longDesription;
	}
	public void setLongDesription(String longDesription)
	{
		this.longDesription = longDesription;
	}
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	public String getImports()
	{
		return imports;
	}
	public void setImports(String imports)
	{
		this.imports = imports;
	}
	public String getExports()
	{
		return exports;
	}
	public void setExports(String exports)
	{
		this.exports = exports;
	}
	public String getUri()
	{
		return uri;
	}
	public void setUri(String uri)
	{
		this.uri = uri;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Bundle Descriptor");
		sb.append("\n  Name: " + this.bundleName);
		sb.append("\n  Symbolic Name: " + this.bundleSymbolicName);
		sb.append("\n  Description: " + this.description);
		sb.append("\n  Version: " + this.version);
		sb.append("\n  Imports: " + this.imports);
		sb.append("\n  Exports: " + this.exports);
		sb.append("\n");
		return sb.toString();
	}
}
