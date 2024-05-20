/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Controller;

import com.example.TravelinVServer.Jwt.JwtTokenProvider;
import com.example.TravelinVServer.Modal.Post;
import com.example.TravelinVServer.Modal.Province;
import com.example.TravelinVServer.Modal.User;
import com.example.TravelinVServer.RequestsDTO.PostRequest;
import com.example.TravelinVServer.Responese.AuthorPostResponse;
import com.example.TravelinVServer.Responese.CommentResponse;
import com.example.TravelinVServer.Responese.FeaturedPostResponse;
import com.example.TravelinVServer.Responese.GoToResponse;
import com.example.TravelinVServer.Responese.PostResponse;
import com.example.TravelinVServer.Responese.PostUserProvinceResponse;
import com.example.TravelinVServer.Responese.PostUserResponse;
import com.example.TravelinVServer.Responese.RecentPostResponse;
import com.example.TravelinVServer.Responese.RelatedPostResponse;
import com.example.TravelinVServer.Service.AzureBlobStorageService;
import com.example.TravelinVServer.Service.CommentService;
import com.example.TravelinVServer.Service.DescriptionService;
import com.example.TravelinVServer.Service.LikeService;
import com.example.TravelinVServer.Service.PostService;
import com.example.TravelinVServer.Service.ProvinceService;
import com.example.TravelinVServer.Service.SubCommentService;
import com.example.TravelinVServer.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping(path = "/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private DescriptionService descriptionService;
    @Autowired
    private AzureBlobStorageService azureBlobStorageService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private SubCommentService subCommentService;
    @Autowired
    CommentService commentSercive;

    @GetMapping(value = "/sortbystatus")
    @PreAuthorize("hasAnyAuthority('ROLE_COLLABORATOR', 'ROLE_ADMIN')")
    public ResponseEntity<List<PostResponse>> getSortPost() {

        try {
            List<PostResponse> res = postService.handleGetPostSortByStatus();
            return res != null ? ResponseEntity.ok(res) : ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/author/{id_post}")
    public ResponseEntity<List<AuthorPostResponse>> getAuthorPost(@PathVariable("id_post") Integer id_post) {
        try {
            List<AuthorPostResponse> res = postService.handleGetAuthorPost(id_post);
            if (id_post != null && res != null) {

                return ResponseEntity.ok(res);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/updatePostStatus/{id_post}")
    @PreAuthorize("hasAnyAuthority('ROLE_COLLABORATOR', 'ROLE_ADMIN')")
    public ResponseEntity<String> updatePostStatus(@PathVariable("id_post") Integer id_post) {

        try {
            if (id_post != null && postService.handleUpdatePostStatus(id_post)) {
                return ResponseEntity.ok("Update successfully");
            } else {
                return ResponseEntity.badRequest().body("Post with ID " + id_post + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/public/getAllPost/v1")
    public ResponseEntity<List<PostResponse>> getAllPost() {
        try {
            List<PostResponse> postList = postService.handleGetAllPost();
            if (postList != null && !postList.isEmpty()) {
                return ResponseEntity.ok(postList);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            // Log the exception for further investigation
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/public/getPostAndProvince")
    public ResponseEntity<List<PostResponse>> getPostAndProvince() {
        try {
            List<PostResponse> postList = postService.handleGetPostAndProvince();
            if (postList != null && !postList.isEmpty()) {
                return ResponseEntity.ok(postList);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            // Log the exception for further investigation
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/public/{id_post}")
    public ResponseEntity<PostResponse> getPostByID(@PathVariable("id_post") Integer id_post) {
        try {
            PostResponse postList = postService.handleGetPostResByID(id_post);
            if (postList != null) {
                return ResponseEntity.ok(postList);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            // Log the exception for further investigation
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/public/getFeaturedPost")
    public ResponseEntity<List<FeaturedPostResponse>> getFeaturedPost() {
        try {
            List<FeaturedPostResponse> postList = postService.handleGetFeaturedPost();
            if (postList != null && !postList.isEmpty()) {
                return ResponseEntity.ok(postList);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            // Log the exception for further investigation
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/public/getRecentPost")
    public ResponseEntity<List<RecentPostResponse>> getRecentPost() {
        try {
            List<RecentPostResponse> postList = postService.handleGetRecentPost();
            if (postList != null && !postList.isEmpty()) {
                return ResponseEntity.ok(postList);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            // Log the exception for further investigation
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/public/getGoToProvince")
    public ResponseEntity<List<GoToResponse>> getGoToProvince() {
        try {
            List<GoToResponse> provinceList = postService.handleGetGotoProvince();
            if (provinceList != null && !provinceList.isEmpty()) {
                return ResponseEntity.ok(provinceList);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            // Log the exception for further investigation
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/public/getPostUserApproved/{username}")
    public ResponseEntity<List<PostUserResponse>> getPostUserApproved(@PathVariable("username") String username) {
        try {
            List<PostUserResponse> res = postService.handleGetPostUser(username);
            return res != null ? ResponseEntity.ok().body(res) : ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/public/getPostUserPostLike/{username}")
    public ResponseEntity<List<PostUserProvinceResponse>> getPostLike(@PathVariable("username") String username) {
        try {
            List<PostUserProvinceResponse> res = postService.handleGetPostLike(username);
            return res != null ? ResponseEntity.ok().body(res) : ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/public/getPostUserPending/{username}")
    public ResponseEntity<List<PostUserResponse>> getPostUserPending(@PathVariable("username") String username) {
        try {
            List<PostUserResponse> res = postService.handleGetPostUserPending(username);
            return res != null ? ResponseEntity.ok().body(res) : ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/public/getRelatedPost/{idProvince}/{idPost}")
    public ResponseEntity<List<RelatedPostResponse>> getRelatedPost(
            @PathVariable("idProvince") int idProvince,
            @PathVariable("idPost") int idPost
    ) {
        try {
            Province province = provinceService.getProvinceFromID(idProvince);
            List<RelatedPostResponse> postList = postService.handleGetRelatedPost(idPost, province);
            if (postList != null && !postList.isEmpty()) {
                return ResponseEntity.ok(postList);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            // Log the exception for further investigation
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/createPost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> createPost(
            @RequestPart(value = "postRequest") PostRequest postRequest,
            @RequestPart(value = "postImage", required = false) MultipartFile postImage,
            //            @RequestPart(value = "DesImage1", required = false) MultipartFile DesImage1,
            //            @RequestPart(value = "DesImage2", required = false) MultipartFile DesImage2,
            HttpServletRequest request) throws ServletException, IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        try {
            // Get province information
            Province province = provinceService.getProvinceFromID(postRequest.getId_province());
            if (province == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Province not found");
            }
            // Get user information
            String username = null;
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                try {
                    username = jwtTokenProvider.extractUsername(token);
                } catch (Exception e) {
                    // Log the error
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
                }
            }
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
            User user = userService.getUserInfo(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Create and save the post
            Post post = new Post();
            String des_image1 = null;
            String des_image2 = null;
            try {
                if (postImage != null) {
                    post.setImage(azureBlobStorageService.uploadImage(postImage));
                }
//                if (DesImage1 != null) {
//                    des_image1 = azureBlobStorageService.uploadImage(DesImage1);
//                }
//                if (DesImage2 != null) {
//                    des_image2 = azureBlobStorageService.uploadImage(DesImage2);
//                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            post.setUser(user);
            post.setPost_name(postRequest.getTitle());
            post.setDemoDescription(postRequest.getDemo_description());
            post.setStatus("Pending");
            post.setProvince(province);
            post.setDate_time(date);
            postService.createPost(post);
//            descriptionService.createDes(postRequest, post, des_image1, des_image2);
            return ResponseEntity.status(HttpStatus.CREATED).body(post.getId_post().toString());
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred" + e);
        }
    }

    @DeleteMapping(value = "/delete/{id_post}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> deletePost(@PathVariable("id_post") Integer id_post) {
        try {
            if (id_post != null) {
                boolean desResult = descriptionService.handleDeleteDescriptionByPostId(id_post);
                List<CommentResponse> comment = commentSercive.handleGetCommentByPostID(id_post);
                // Iterate over a copy of the comment list to avoid modification while iterating
                List<CommentResponse> commentCopy = new ArrayList<>(comment);
                for (CommentResponse commentResponse : commentCopy) {
                    int id_comment = commentResponse.getId_comment();
                    // Delete sub-comments
                    boolean subcmtResult = subCommentService.handleDeleteSubCommnetByCommentID(id_comment);
                    // Delete comment
                    commentSercive.handleDeleteCommentByID(id_comment);
                }
                boolean likeResult = likeService.handleDeleteAllLikeByPostID(id_post);
                boolean postResult = postService.handleDeletPostById(id_post);
                return ResponseEntity.ok().body("Delete Successfully");
            }
            return ResponseEntity.badRequest().body("id post is required");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
