package ASM1.demo.DTO;

import org.springframework.web.multipart.MultipartFile;

public class ImageFile {
    private String name;
    private MultipartFile image;//chuyển đổi trường image sang multipartFile
    private String description;

    public MultipartFile getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageFile(String description, MultipartFile image, String name) {
        this.description = description;
        this.image = image;
        this.name = name;
    }



}
