package com.example.customer.util;

public class CustomerIdDeserializer {
    public String format(String account_id){
        try {
            String Prefix="";
            if(account_id.startsWith("+")){account_id=account_id.substring(1);}
            if(account_id.startsWith("0")){
                if(account_id.length()==9 || account_id.length() == 10){Prefix="855"+account_id.substring(1);
                }else{Prefix= "FALSE";}
            }else if(account_id.startsWith("855")){
                if(account_id.length()==11 || account_id.length() == 12){Prefix=account_id;
                }else if(account_id.length()==8 || account_id.length() ==9){Prefix="855"+account_id;
                }else{Prefix= "FALSE";}
            }else{
                if(account_id.length()==8 || account_id.length() == 9){Prefix="855"+account_id;
                }else{Prefix= "FALSE";}
            }
            return Prefix;
        } catch (Exception ex) {
            return "FALSE";
        }
    }
}
