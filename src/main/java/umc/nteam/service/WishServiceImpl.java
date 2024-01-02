package umc.nteam.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.nteam.converter.WishConverter;
import umc.nteam.domain.Wish;
import umc.nteam.repository.WishRepository;
import umc.nteam.web.dto.WishDto.WishAddRequestDto;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService{

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final WishRepository wishRepository;
    private final AmazonS3Client amazonS3Client;

    @Override
    @Transactional
    public Wish createWish(WishAddRequestDto requestDto) throws IOException {
        String imageUrl = imageToS3(requestDto.getImage());
        Wish wish = WishConverter.toWish(requestDto, imageUrl);
        return wishRepository.save(wish);
    }


    private String imageToS3(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
        return "https://s3.ap-northeast-2.amazonaws.com/" + bucket + "/" + fileName;
    }
}
