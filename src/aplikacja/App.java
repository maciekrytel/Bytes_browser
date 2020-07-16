package aplikacja;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class App {


    public byte [] choosenData;
    public String filePath;
    public List<File> fileList;
    public String status;

    public boolean compareFiles(byte[] data2, byte [] data3) throws Exception {
            try {
                for (int a = 0; a < fileList.size(); a++) {
                    int b = 1;
                    System.out.println("Plik: " + fileList.get(a).getName());
                    byte[] readFile = Files.readAllBytes(Paths.get(fileList.get(a).getPath()));
                    choosenData = readFile;
                    String choosenFilePath = fileList.get(a).getAbsolutePath();
                    filePath = choosenFilePath;

                    byte[] firstBytes = data2;
                    String firstBytesString = new String(firstBytes);
                    System.out.println(firstBytesString);

                    byte[] secondBytes = data3;
                    String secondBytesString = new String(secondBytes);
                    System.out.println(secondBytesString);

                    int bufferSize = Math.abs(firstBytes.length - secondBytes.length);
                    int j = 0;

                    for (int i = 0; i < choosenData.length; i++) {

                        if (firstBytes[j] == choosenData[i]) {
                            j++;
                        } else {
                            j = 0;
                        }

                        if (j == firstBytes.length) {
                            j = 0;
                            System.out.println("Znaleziono szukany ciąg");

                            if (secondBytes.length > firstBytes.length) {
                                byte[] buffer = new byte[bufferSize];
                                Arrays.fill(buffer, (byte) 1);
                                ByteArrayOutputStream output = new ByteArrayOutputStream();

                                byte[] output2;
                                byte[] output3;
                                output2 = Arrays.copyOfRange(choosenData, 0, i + 1);
                                output3 = Arrays.copyOfRange(choosenData, i + 1, choosenData.length);

                                output.write(output2);
                                output.write(buffer);
                                output.write(output3);

                                byte[] out = output.toByteArray();


                                choosenData = out;
                                FileOutputStream fileToSaveFirst = new FileOutputStream(filePath);
                                fileToSaveFirst.write(choosenData);
                                fileToSaveFirst.close();

                                int z = 0;

                                for (int k = i - (firstBytes.length - 1); k < (i + 1 + bufferSize); k++) {
                                    choosenData[k] = secondBytes[z];
                                    z++;
                                }
                                System.out.println("Zastapiono ciąg");
                                b++;
                            } else if (secondBytes.length < firstBytes.length) {
                                ByteArrayOutputStream output = new ByteArrayOutputStream();
                                byte[] output2;
                                byte[] output3;
                                output2 = Arrays.copyOfRange(choosenData, 0, i + 1 - bufferSize);
                                output3 = Arrays.copyOfRange(choosenData, i + 1, choosenData.length);
                                output.write(output2);
                                output.write(output3);
                                byte[] out = output.toByteArray();
                                choosenData = out;
                                FileOutputStream fileToSaveFirst = new FileOutputStream(filePath);
                                fileToSaveFirst.write(choosenData);
                                fileToSaveFirst.close();

                                int z = 0;

                                for (int k = i - (firstBytes.length - 1); k < (i + 1 - bufferSize); k++) {
                                    choosenData[k] = secondBytes[z];
                                    z++;
                                }
                                System.out.println("Zastapiono ciąg");
                                b++;
                            } else {
                                if (! firstBytesString.equals(secondBytesString)) {
                                    int z = 0;

                                    for (int k = i - (firstBytes.length - 1); k < (i + 1); k++) {
                                        choosenData[k] = secondBytes[z];
                                        z++;
                                    }
                                    System.out.println("Zastapiono ciąg");
                                    b++;
                                }
                                else{
                                    b=0;
                                }
                            }
                        } else {
                            System.out.println("Nie znaleziono szukanego ciągu");
                        }
                    }
                    FileOutputStream fileToSave = new FileOutputStream(filePath);
                    fileToSave.write(choosenData);
                    fileToSave.close();

                    if (b > 1){
                        status="Znaleziono i zastąpiono " + (b-1) + " szukanych ciągów";
                    }
                    else if (b==0){
                        status="Próbujesz zamienić dwa te same ciągi";
                    }
                    else{
                        status="Nie znaleziono żadnego ciągu";
                    }

                }
                    return true;

            } catch(Exception e){
                if (choosenData== null){
                    status="Nie wybrałeś pliku. Spróbuj ponownie";
                }
                else if (data2.length == 0) {
                    status="Nie podałeś szukanego ciągu. Spróbuj ponownie";
                }
                else {
                    status = "Spróbuj ponownie";
                }
                e.printStackTrace();
                return false;
            }




    }

    public String getStat(){
        return this.status;
    }

}
