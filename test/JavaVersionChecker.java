

public class JavaVersionChecker {
    public static void main(String[] args) {
        // Get Java version
        String javaVersion = System.getProperty("java.version");
        System.out.println("Java Version: " + javaVersion);

        // Get Java runtime environment version
        String jreVersion = System.getProperty("java.runtime.version");
        System.out.println("JRE Version: " + jreVersion);

        // Get Java Development Kit version
        String jdkVersion = System.getProperty("java.specification.version");
        System.out.println("JDK Version: " + jdkVersion);

        // Get Java vendor details
        String javaVendor = System.getProperty("java.vendor");
        System.out.println("Java Vendor: " + javaVendor);

        // Get Java home directory
        String javaHome = System.getProperty("java.home");
        System.out.println("Java Home Directory: " + javaHome);

        // Check if running a JDK or JRE
        boolean isJdk = System.getenv("JAVA_HOME") != null && System.getenv("JAVA_HOME").contains("jdk");
        if (isJdk) {
            System.out.println("Running JDK: Yes");
        } else {
            System.out.println("Running JDK: No (Likely JRE)");
        }
    }
}
