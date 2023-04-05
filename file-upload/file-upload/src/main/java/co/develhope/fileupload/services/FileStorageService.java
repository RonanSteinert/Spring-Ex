package co.develhope.fileupload.services;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {

    @Autowired
    private Environment environment;

    /**
     *
     * @param file file from uploadController
     * @return new file name with extension
     * @throws IOException if folder is not writtable
     */
    public String upload(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = UUID.randomUUID().toString();
        String completeFileName = newFileName + "." + extension;
        File finalFolder = new File(environment.getProperty("fileRepositoryFolder"));
        if(!finalFolder.exists()) throw new IOException("Final folder does not exist");
        if(!finalFolder.isDirectory()) throw new IOException("Final folder is not a directory");

        File finalDestination = new File(environment.getProperty("fileRepositoryFolder") + "\\" + completeFileName);
        if(finalDestination.exists()) throw new IOException("File conflict");

        file.transferTo(finalDestination);
        return completeFileName;
    }

    public byte[] download(String fileName) throws IOException {
        File fileFromRepository = new File(environment.getProperty("fileRepositoryFolder") + "\\" + fileName);
        if(!fileFromRepository.exists()) throw new IOException("File does not exist");
        return IOUtils.toByteArray(new FileInputStream(fileFromRepository));
    }

    @SneakyThrows
    public void remove(String fileName) {
        File fileFromRepository = new File(environment.getProperty("fileRepositoryFolder") + "\\" + fileName);
        if(!fileFromRepository.exists()) return;
        boolean deleteResult = fileFromRepository.delete();
        if(deleteResult == false) throw new Exception("Cannot delete file");
    }
}
