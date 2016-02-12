package com.rtn.drm.rounding.impl;

import java.lang.String;
public class RoundingService {

	public RoundingService(){
		round(9.12, 1, "D");
	}
	
	public double round(double number, int decimalPlace, String DI){

	    String numberStr = "" + number;
	    int placeCheck = 0;

	    if(DI == "D")
	    {
	        placeCheck = decimalPlace;      
	    }
	    else if(DI == "I")
	    {
	        placeCheck = decimalPlace - 1;
	    }

	    String temp = numberStr;
	    int TisNull = 0;
	    int totalLength = 0;
	    int decimalReached = 0;
	    int digitsBeforeDecimal = 0;
	    int digitsAfterDecimal = 0;
	    int decimalAdded = 0;

	    while(TisNull == 0)
	    {
	        totalLength = totalLength+1;
	        
	        String H = temp.substring(0,1);
	        String T = temp.substring(1);
	        
	        if( T == null){
	            TisNull = 1;
	        }
	        temp = T;

	        if(H == ".")
	        {
	            decimalReached = 1;
	        }else{
	            if(decimalReached == 0)
	            {
	                digitsBeforeDecimal++; 
	            }
	            if(decimalReached == 1)
	            {
	                digitsAfterDecimal++; 
	            }
	        }
	    }
	    
	    int lengthCheck = totalLength;
	    if(digitsAfterDecimal == 0 && DI == "D")
	    {
	        decimalAdded = 1;
	        lengthCheck = lengthCheck + placeCheck + 1;
	    }
  
	    if(DI == "D")
	    {
	         digitsAfterDecimal = 0;    
	    }else{
	         digitsAfterDecimal = 1;
	    }
	    decimalReached = 0;
	    int lengthDif = digitsBeforeDecimal - placeCheck;
	    int digitsBeforeDecimal2 = digitsBeforeDecimal -1;
	    int roundCheck = 0;
	    int roundCheck2 = 0;
	    int negative = 0;
	    String newString = "";
	    double roundedNumber = 0;
	    while( !(lengthCheck == 0))
	    {
		    String H1 = numberStr.substring(0,1);
		    String H2 = numberStr.substring(1,2);	 
		    String H3 = numberStr.substring(2,3);
		    String T = numberStr.substring(3);	 
		        
	       if(DI == "D"){
	            if (digitsBeforeDecimal2 == 0){
	                H2 = ".";
	            }else if(digitsBeforeDecimal2 < -1 && decimalAdded == 1){
	                H1 = "0";
	                H2 = "0";
	                H3 = "0";
	            }
	            if(H1 == "."){
	                decimalReached = 1;
	            }            
	       }
	       if(H1 == "-"){
	            negative = 1;
	       }
	       if(DI == "D"){
	            if(!(digitsBeforeDecimal >= placeCheck)){
	                newString = newString + H1;
	            }else{
	                if(digitsAfterDecimal == placeCheck){
	                    if(H2 == "0" || H2 == "1" || H2 == "2" || H2 == "3" || H2 == "4" || H2 == "n"){
	                	    newString = newString + H1;
	                        roundedNumber = Double.parseDouble(newString);
	                    }else if(negative == 0){
	                        newString = newString + H1;
	                        roundedNumber = Double.parseDouble(newString) + Math.pow(0.1,digitsAfterDecimal);
	                    }else if(negative == 1){
	                        newString = newString + H1;
	                        roundedNumber = Double.parseDouble(newString) - Math.pow(0.1,digitsAfterDecimal);
	                    }
	                    roundCheck = 1;
	                    }else if(roundCheck == 1){
	                        newString = newString + "0";
	                    }
	                }
	            }else if(DI == "I"){
	                if( !(digitsAfterDecimal >= lengthDif)){
	                    newString = newString + H1;
	                }else if(digitsAfterDecimal == lengthDif){
	                    if(H2 == "0" || H2 == "1" || H2 == "2" || H2 == "3" || H2 == "4" || H2 == "n"){
	                        roundCheck2 = 1;
	                        H2 = "0";
	                    }else if(H2 == "."){
	                        if(H3 == "0" || H3 == "1" || H3 == "2" || H3 == "3" || H3 == "4" || H3 == "n"){
	                            roundCheck2 = 1;
	                            H3 = "0";                     
	                        }
	                    }
	                    newString = newString + H1;
	                
	                }else if(digitsAfterDecimal <= digitsBeforeDecimal && digitsAfterDecimal > lengthDif){
	                    newString = newString + "0";
	                }
	            }
	            if(DI == "D"){        
	                if(decimalReached == 1){
	                    digitsAfterDecimal++;  
	                }
	            }else if(DI == "I"){
	                if(decimalReached == 0){
	                    digitsAfterDecimal++;  
	                }
	            }        
	            numberStr = H2 + H3 + T;
	            if(DI == "I"){
	                if(H1 == "."){
	                    decimalReached = 1;
	                }
	            }
	            if(DI == "D"){
	                digitsBeforeDecimal--;
	            }
	            lengthCheck = lengthCheck -1;
	        }

	        if(roundCheck2 == 1 && DI == "I"){
                roundedNumber = Double.parseDouble(newString);
	        }else if(roundCheck2 == 0 && DI == "I" && negative == 0){
                roundedNumber = Double.parseDouble(newString) + Math.pow(10,placeCheck);
	        }else if(roundCheck2 == 0 && DI == "I" && negative == 1){
                roundedNumber = Double.parseDouble(newString) - Math.pow(10,placeCheck);
	        }
	    System.out.print(roundedNumber);
	    return roundedNumber;
	}	

	}

