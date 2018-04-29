package crypto;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA {
    private static final String TEST_DATA = "test_data";
    private String privateKeyValue;
    private String publicKeyValue;
    private byte[] realSignature;

    public void keyPairGenerationDemo() throws NoSuchAlgorithmException {
        //Returns a KeyPairGenerator object that generates public/private key pairs for the specified algorithm.
        //This method traverses the list of registered security Providers,
        //starting with the most preferred Provider.
        //A new KeyPairGenerator object encapsulating the
        //KeyPairGeneratorSpi implementation from the first
        //Provider that supports the specified algorithm is returned.
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

        // set key size, specified in num of bits
        // inside generator, an SecureRandom instance is used to generate the key with specified size
        generator.initialize(2048);

        // generate new key pair
        KeyPair keyPair = generator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        System.out.println("Public key info::");
        System.out.println("decimal representation of public exponent: " + publicKey.getPublicExponent().toString());
        System.out.println("algorithm: " + publicKey.getAlgorithm());
        System.out.println("format: " + publicKey.getFormat());
        System.out.println("decimal representation of modulus: " + publicKey.getModulus().toString());
        System.out.println("\n\n");

        System.out.println("Private key info::");
        System.out.println("decimal representation of public exponent: " + privateKey.getPrivateExponent().toString());
        System.out.println("algorithm: " + privateKey.getAlgorithm());
        System.out.println("format: " + privateKey.getFormat());
        System.out.println("decimal representation of modulus: " + privateKey.getModulus().toString());
        System.out.println("isDestroyed: " + privateKey.isDestroyed());

        privateKeyValue = Base64.encode(privateKey.getEncoded()); // give x.509 format encoded public key
        publicKeyValue = Base64.encode(publicKey.getEncoded()); // give pkcs8 format encoded private key
        System.out.println("private key: \n" + privateKeyValue);
        System.out.println("public key: \n" + publicKeyValue);
    }

    public void signDemo() throws Exception{
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyValue));

        /**
         * Key factories are used to convert <I>keys</I> (opaque
         * cryptographic keys of type {@code Key}) into <I>key specifications</I>
         * (transparent representations of the underlying key material), and vice
         * versa.
         *
         * <P> Key factories are bi-directional. That is, they allow you to build an
         * opaque key object from a given key specification (key material), or to
         * retrieve the underlying key material of a key object in a suitable format.
         *
         * <P> Multiple compatible key specifications may exist for the same key.
         * For example, a DSA public key may be specified using
         * {@code DSAPublicKeySpec} or
         * {@code X509EncodedKeySpec}. A key factory can be used to translate
         * between compatible key specifications.
         *
         * <P> The following is an example of how to use a key factory in order to
         * instantiate a DSA public key from its encoding.
         * Assume Alice has received a digital signature from Bob.
         * Bob also sent her his public key (in encoded format) to verify
         * his signature. Alice then performs the following actions:
         *
         * <pre>
         * X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(bobEncodedPubKey);
         * KeyFactory keyFactory = KeyFactory.getInstance("DSA");
         * PublicKey bobPubKey = keyFactory.generatePublic(bobPubKeySpec);
         * Signature sig = Signature.getInstance("DSA");
         * sig.initVerify(bobPubKey);
         * sig.update(data);
         * sig.verify(signature);
         * </pre>
         *
         * Returns a KeyFactory object that converts
         * public/private keys of the specified algorithm.
         *
         * <p> This method traverses the list of registered security Providers,
         * starting with the most preferred Provider.
         * A new KeyFactory object encapsulating the
         * KeyFactorySpi implementation from the first
         * Provider that supports the specified algorithm is returned.
         */
        KeyFactory kf = KeyFactory.getInstance("RSA");
        System.out.println("Key factory algorithm: " + kf.getAlgorithm());

        // Basically, KeyFactory can convert between private / public KeySpec (can also be generated from encoded format) instances and private / public Key instances.
        // But the conversion is not always free, like RSA private key cannot be convert to X509EncodedKeySpec as it's a public key format
        // Specifically, KeySpec must be RSAPrivate(Crt)KeySpec or PKCS8EncodedKeySpec for RSA private keys
        PrivateKey privateKey = kf.generatePrivate(spec);
        RSAPrivateKeySpec rsaPrivateKeySpec = kf.getKeySpec(privateKey, RSAPrivateKeySpec.class);
        System.out.println("Converted private key to RSAPrivateKeySpec: \nmodulus: " + Base64.encode(rsaPrivateKeySpec.getModulus()) + "\nexponent: " + Base64.encode(rsaPrivateKeySpec.getPrivateExponent()));

        /**
         * The Signature class is used to provide applications the functionality
         * of a digital signature algorithm. Digital signatures are used for
         * authentication and integrity assurance of digital data.
         *
         * <p> The signature algorithm can be, among others, the NIST standard
         * DSA, using DSA and SHA-1. The DSA algorithm using the
         * SHA-1 message digest algorithm can be specified as {@code SHA1withDSA}.
         * In the case of RSA, there are multiple choices for the message digest
         * algorithm, so the signing algorithm could be specified as, for example,
         * {@code MD2withRSA}, {@code MD5withRSA}, or {@code SHA1withRSA}.
         * The algorithm name must be specified, as there is no default.
         *
         * <p> A Signature object can be used to generate and verify digital
         * signatures.
         *
         * <p> There are three phases to the use of a Signature object for
         * either signing data or verifying a signature:<ol>
         *
         * <li>Initialization, with either
         *
         *     <ul>
         *
         *     <li>a public key, which initializes the signature for
         *     verification (see {@link #initVerify(PublicKey) initVerify}), or
         *
         *     <li>a private key (and optionally a Secure Random Number Generator),
         *     which initializes the signature for signing
         *     (see {@link #initSign(PrivateKey)}
         *     and {@link #initSign(PrivateKey, SecureRandom)}).
         *
         *     </ul>
         */
        Signature signature = Signature.getInstance("SHA1withRSA"); // note that message digest algorithm is required, put SHA1 here
        signature.initSign(privateKey);
        signature.update(TEST_DATA.getBytes()); // update signature payload, also can be from an input stream
        realSignature = signature.sign();
        System.out.println("Generated signature: " + Base64.encode(realSignature));
    }

    public void verifyDemo() throws Exception{
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.decode(publicKeyValue));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(spec);
        Signature signature = Signature.getInstance("SHA1withRSA");

        // Signature instance can do both sign & verify
        // When do sign, call initSign() & pass in privateKey
        // When do verify, call initVerify() & pass in publicKey
        signature.initVerify(publicKey);
        signature.update(TEST_DATA.getBytes()); // update signature payload

        boolean r = signature.verify(realSignature); // verify the signature
        assert r;
        System.out.println("Signature verify success, r-" + Boolean.toString(r));
    }

    public static void main(String[] args) throws Exception {
        RSA rsa = new RSA();
        rsa.keyPairGenerationDemo();
        rsa.signDemo();
        rsa.verifyDemo();
    }
}
