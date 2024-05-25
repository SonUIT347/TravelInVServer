/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Service;

import com.example.TravelinVServer.Modal.Post;
import com.example.TravelinVServer.Modal.Province;
import com.example.TravelinVServer.Repository.PostRepository;
import com.example.TravelinVServer.Responese.AuthorPostResponse;
import com.example.TravelinVServer.Responese.FeaturedPostResponse;
import com.example.TravelinVServer.Responese.GoToResponse;
import com.example.TravelinVServer.Responese.PostResponse;
import com.example.TravelinVServer.Responese.PostUserProvinceResponse;
import com.example.TravelinVServer.Responese.PostUserResponse;
import com.example.TravelinVServer.Responese.RecentPostResponse;
import com.example.TravelinVServer.Responese.RelatedPostResponse;
import com.example.TravelinVServer.Until.Helper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postReository;
    @Autowired
    private Helper helper;

    public String createPost(Post post) {
        postReository.save(post);
        return "success";
    }

    public List<PostUserResponse> handleGetPostUser(String username) {
        try {
            if (username != null) {
                List<PostUserResponse> res = postReository.getPostByUsernameApproved(username);
                return res;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PostUserResponse> handleGetPostUserPending(String username) {
        try {
            if (username != null) {
                List<PostUserResponse> res = postReository.getPostByUsernamePending(username);
                return res;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PostUserProvinceResponse> handleGetPostLike(String username) {
        try {
            if (username != null) {
                List<PostUserProvinceResponse> res = postReository.getPostLike(username);
                return res;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PostResponse> handleGetAllPost() {
        List<Post> postList = null;
        //binding data for response
        try {
            postList = postReository.getAllPost();
            List<PostResponse> postRess = postList
                    .stream()
                    .map(postRes -> {
                        PostResponse res = new PostResponse();
                        res.setDate_time(postRes.getDate_time());
                        res.setId_post(postRes.getId_post());
                        res.setDemo_description(postRes.getDemoDescription());
                        res.setImage(postRes.getImage());
                        res.setStatus(postRes.getStatus());
                        res.setPost_name(postRes.getPost_name());
                        res.setId_user(postRes.getUser().getId_user());
                        res.setId_province(postRes.getProvince().getId_province());
                        return res;
                    })
                    .collect(Collectors.toList());
            return postRess;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FeaturedPostResponse> handleGetFeaturedPost() {
        List<FeaturedPostResponse> featuredPosts = null;
        try {
            featuredPosts = postReository.getFeaturedPost();
            if (featuredPosts.size() > helper.getSQL_ROWS_LIMIT_3()) {
                featuredPosts = featuredPosts.subList(0, helper.getSQL_ROWS_LIMIT_3());
            }
            return featuredPosts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RecentPostResponse> handleGetRecentPost() {
        List<RecentPostResponse> recentPosts = null;
        try {
            recentPosts = postReository.getRecentPost();
            if (recentPosts.size() > helper.getSQL_ROWS_LIMIT_3()) {
                recentPosts = recentPosts.subList(0, helper.getSQL_ROWS_LIMIT_3());
            }
            return recentPosts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RelatedPostResponse> handleGetRelatedPost(Integer postId, Province province) {
        List<RelatedPostResponse> relatedPosts = null;
        try {

            relatedPosts = postReository.getRelatedPost(postId, province);
            if (relatedPosts.size() > helper.getSQL_ROWS_LIMIT_3()) {
                relatedPosts = relatedPosts.subList(0, helper.getSQL_ROWS_LIMIT_3());
            }
            return relatedPosts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GoToResponse> handleGetGotoProvince() {
        List<GoToResponse> gotoProvince = null;
        try {
            gotoProvince = postReository.getGoto();
            if (gotoProvince.size() > helper.getSQL_ROWS_LIMIT_9()) {
                gotoProvince = gotoProvince.subList(0, helper.getSQL_ROWS_LIMIT_9());
            }
            return gotoProvince;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean handleUpdatePostStatus(Integer id_post) {
        try {
            if (id_post != null && postReository.existsById(id_post)) {
                postReository.updatePostStatus(id_post);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public Post handleGetPostByID(Integer id_post) {
        try {
            Post post = new Post();
            if (id_post != null && postReository.existsById(id_post)) {
                Optional<Post> opPost = postReository.findById(id_post);
                if (opPost.isPresent()) {
                    post.setComment(opPost.get().getComment());
                    post.setDate_time(opPost.get().getDate_time());
                    post.setDemoDescription(opPost.get().getDemoDescription());
                    post.setDescription(opPost.get().getDescription());
                    post.setImage(opPost.get().getImage());
                    post.setId_post(id_post);
                    post.setPost_name(opPost.get().getPost_name());
                    post.setLikes(opPost.get().getLikes());
                    post.setProvince(opPost.get().getProvince());
                    post.setStatus(opPost.get().getStatus());
                    post.setUser(opPost.get().getUser());
                    return post;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AuthorPostResponse> handleGetAuthorPost(Integer id_post) {
        try {
            if (postReository.existsById(id_post)) {
                return postReository.getAuthorPost(id_post);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PostResponse handleGetPostResByID(Integer id_post) {
        try {
            PostResponse post = new PostResponse();
            if (id_post != null && postReository.existsById(id_post)) {
                Optional<Post> opPost = postReository.findById(id_post);
                if (opPost.isPresent()) {
//                    post.setComment(opPost.get().getComment());
                    post.setDate_time(opPost.get().getDate_time());
                    post.setDemo_description(opPost.get().getDemoDescription());
//                    post.setDescription(opPost.get().getDescription());
                    post.setImage(opPost.get().getImage());
                    post.setId_post(id_post);
                    post.setPost_name(opPost.get().getPost_name());
//                    post.setLikes(opPost.get().getLikes());
                    post.setId_province(opPost.get().getProvince().getId_province());
                    post.setStatus(opPost.get().getStatus());
                    post.setId_user(opPost.get().getUser().getId_user());
                    return post;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public List<AuthorPostResponse> handleGetAuthorPost(Integer id_post) {
//        try {
//            if (postReository.existsById(id_post)) {
//                return postReository.getAuthorPost(id_post);
//            }
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public List<PostResponse> handleGetPostSortByStatus() {
        try {
            List<PostResponse> result = postReository.getPostSortByStatusAndDate();
            return result != null ? result : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PostResponse> handleGetPostAndProvince() {
        List<Object[]> postList = null;
        try {
            postList = postReository.getPostAndProvince();
            List<PostResponse> postRess = postList
                    .stream()
                    .map(postRes -> {
                        PostResponse res = new PostResponse();
                        res.setId_post((Integer) postRes[0]);
                        res.setProvince_name((String) postRes[1]);
                        res.setId_province((Integer) postRes[2]);
                        res.setImage((String) postRes[3]);
                        // res.setDate_time((Date) postRes[1]);
                        // res.setPost_name((String) postRes[2]);
                        return res;
                    })
                    .collect(Collectors.toList());
            return postRess;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean handleDeletPostById(Integer id_post) {
        try {
            if (id_post != null && postReository.existsById(id_post)) {
                postReository.deleteById(id_post);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
