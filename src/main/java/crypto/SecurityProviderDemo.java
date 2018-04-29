package crypto;

import java.security.Provider;
import java.security.Security;

public class SecurityProviderDemo {
    public void demo(){
        //FYI, print all the security providers & services (algorithms) here
        Provider[] allProviders = Security.getProviders();
        for(Provider p : allProviders){
            System.out.println(p.getName() + ":");
            for (Provider.Service s : p.getServices()){
                System.out.print(s.getAlgorithm() + "|");
            }
            System.out.println("\n\n");
        }

        /*
        * Output::
        *
        * SUN:
        * NativePRNG|SHA1PRNG|NativePRNGBlocking|NativePRNGNonBlocking|SHA1withDSA|NONEwithDSA|SHA224withDSA|SHA256withDSA|DSA|MD2|MD5|SHA|SHA-224|SHA-256|SHA-384|SHA-512|DSA|DSA|DSA|X.509|JKS|CaseExactJKS|DKS|JavaPolicy|JavaLoginConfig|PKIX|PKIX|LDAP|Collection|com.sun.security.IndexedCollection|
        *
        * SunRsaSign:
        * RSA|RSA|MD2withRSA|MD5withRSA|SHA1withRSA|SHA224withRSA|SHA256withRSA|SHA384withRSA|SHA512withRSA|
        *
        * SunEC:
        * EC|EC|NONEwithECDSA|SHA1withECDSA|SHA224withECDSA|SHA256withECDSA|SHA384withECDSA|SHA512withECDSA|EC|ECDH|
        *
        * SunJSSE:
        * RSA|RSA|MD2withRSA|MD5withRSA|SHA1withRSA|MD5andSHA1withRSA|SunX509|NewSunX509|SunX509|PKIX|TLSv1|TLSv1.1|TLSv1.2|TLS|Default|PKCS12|
        *
        * SunJCE:
        * RSA|DES|DESede|DESedeWrap|PBEWithMD5AndDES|PBEWithMD5AndTripleDES|PBEWithSHA1AndDESede|PBEWithSHA1AndRC2_40|PBEWithSHA1AndRC2_128|PBEWithSHA1AndRC4_40|PBEWithSHA1AndRC4_128|PBEWithHmacSHA1AndAES_128|PBEWithHmacSHA224AndAES_128|PBEWithHmacSHA256AndAES_128|PBEWithHmacSHA384AndAES_128|PBEWithHmacSHA512AndAES_128|PBEWithHmacSHA1AndAES_256|PBEWithHmacSHA224AndAES_256|PBEWithHmacSHA256AndAES_256|PBEWithHmacSHA384AndAES_256|PBEWithHmacSHA512AndAES_256|Blowfish|AES|AES_128/ECB/NoPadding|AES_128/CBC/NoPadding|AES_128/OFB/NoPadding|AES_128/CFB/NoPadding|AES_128/GCM/NoPadding|AES_192/ECB/NoPadding|AES_192/CBC/NoPadding|AES_192/OFB/NoPadding|AES_192/CFB/NoPadding|AES_192/GCM/NoPadding|AES_256/ECB/NoPadding|AES_256/CBC/NoPadding|AES_256/OFB/NoPadding|AES_256/CFB/NoPadding|AES_256/GCM/NoPadding|AESWrap|AESWrap_128|AESWrap_192|AESWrap_256|RC2|ARCFOUR|DES|DESede|Blowfish|AES|RC2|ARCFOUR|HmacMD5|HmacSHA1|HmacSHA224|HmacSHA256|HmacSHA384|HmacSHA512|DiffieHellman|DiffieHellman|DiffieHellman|DiffieHellman|DES|DESede|PBE|PBEWithMD5AndDES|PBEWithMD5AndTripleDES|PBEWithSHA1AndDESede|PBEWithSHA1AndRC2_40|PBEWithSHA1AndRC2_128|PBEWithSHA1AndRC4_40|PBEWithSHA1AndRC4_128|PBES2|PBEWithHmacSHA1AndAES_128|PBEWithHmacSHA224AndAES_128|PBEWithHmacSHA256AndAES_128|PBEWithHmacSHA384AndAES_128|PBEWithHmacSHA512AndAES_128|PBEWithHmacSHA1AndAES_256|PBEWithHmacSHA224AndAES_256|PBEWithHmacSHA256AndAES_256|PBEWithHmacSHA384AndAES_256|PBEWithHmacSHA512AndAES_256|Blowfish|AES|GCM|RC2|OAEP|DiffieHellman|DES|DESede|PBEWithMD5AndDES|PBEWithMD5AndTripleDES|PBEWithSHA1AndDESede|PBEWithSHA1AndRC2_40|PBEWithSHA1AndRC2_128|PBEWithSHA1AndRC4_40|PBEWithSHA1AndRC4_128|PBEWithHmacSHA1AndAES_128|PBEWithHmacSHA224AndAES_128|PBEWithHmacSHA256AndAES_128|PBEWithHmacSHA384AndAES_128|PBEWithHmacSHA512AndAES_128|PBEWithHmacSHA1AndAES_256|PBEWithHmacSHA224AndAES_256|PBEWithHmacSHA256AndAES_256|PBEWithHmacSHA384AndAES_256|PBEWithHmacSHA512AndAES_256|PBKDF2WithHmacSHA1|PBKDF2WithHmacSHA224|PBKDF2WithHmacSHA256|PBKDF2WithHmacSHA384|PBKDF2WithHmacSHA512|HmacMD5|HmacSHA1|HmacSHA224|HmacSHA256|HmacSHA384|HmacSHA512|HmacPBESHA1|PBEWithHmacSHA1|PBEWithHmacSHA224|PBEWithHmacSHA256|PBEWithHmacSHA384|PBEWithHmacSHA512|SslMacMD5|SslMacSHA1|JCEKS|SunTlsPrf|SunTls12Prf|SunTlsMasterSecret|SunTlsKeyMaterial|SunTlsRsaPremasterSecret|
        *
        * SunJGSS:
        * 1.2840.113554.1.2.2|1.3.6.1.5.5.2|
        *
        * SunSASL:
        * DIGEST-MD5|NTLM|GSSAPI|EXTERNAL|PLAIN|CRAM-MD5|CRAM-MD5|GSSAPI|DIGEST-MD5|NTLM|
        *
        * XMLDSig:
        * http://www.w3.org/2006/12/xml-c14n11#WithComments|http://www.w3.org/2000/09/xmldsig#base64|http://www.w3.org/TR/1999/REC-xslt-19991116|http://www.w3.org/2001/10/xml-exc-c14n#|http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments|http://www.w3.org/2000/09/xmldsig#enveloped-signature|http://www.w3.org/2002/06/xmldsig-filter2|DOM|http://www.w3.org/TR/2001/REC-xml-c14n-20010315|http://www.w3.org/2001/10/xml-exc-c14n#WithComments|http://www.w3.org/2006/12/xml-c14n11|http://www.w3.org/TR/1999/REC-xpath-19991116|DOM|
        *
        * SunPCSC:
        * PC/SC|
        *
        * Apple:
        * KeychainStore|
        */
    }

    public static void main(String[] args){
        SecurityProviderDemo demo = new SecurityProviderDemo();
        demo.demo();
    }
}
