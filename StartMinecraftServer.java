import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartMinecraftServer {
    public static void main(String[] args) {
        String serverPath = "forge-1.21.1-52.1.0-shim.jar";
        String java = "C:\\Program Files\\Java\\jdk-21\\bin\\java.exe";

        String[] command = {java, "-Xmx1024M", "-Xms1024M", "-jar", serverPath, "nogui"};

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true); // Capture aussi les erreurs
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
