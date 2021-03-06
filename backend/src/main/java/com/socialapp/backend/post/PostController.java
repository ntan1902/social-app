package com.socialapp.backend.post;

import com.socialapp.backend.user.UserDTO;
import com.socialapp.backend.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class PostController {
    private final PostService postService;
    private final FileUploadUtil fileUploadUtil;
    private static final String DIRECTORY_PATH = "post-images";

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String uri = fileUploadUtil.uploadFile(multipartFile, "/api/v1/posts/images/", DIRECTORY_PATH);
        return ResponseEntity.ok(uri);
    }

    @GetMapping(
            value = "/images/{imageName}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public byte[] getImageWithMediaType(@PathVariable(name = "imageName") String fileName) throws IOException {
        return fileUploadUtil.load(DIRECTORY_PATH, fileName);
    }

    @PostMapping
    public ResponseEntity<?> insertPost(@RequestBody @Valid PostDTO postDTO) {
        this.postService.insertPost(postDTO);
        return new ResponseEntity<>("Insert post successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO postDTO) {
        this.postService.updatePost(id, postDTO);
        return new ResponseEntity<>("Update post successfully", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        this.postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findPost(@PathVariable("id") Long id){
        PostDTO res = this.postService.findPostById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/liked-users")
    public ResponseEntity<List<UserDTO>> findLikedUsers(@PathVariable("id") Long id) {
        List<UserDTO> res = this.postService.findLikedUsers(id);
        return ResponseEntity.ok(res);
    }

}
