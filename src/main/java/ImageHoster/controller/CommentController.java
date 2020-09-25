package ImageHoster.controller;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    ImageService imageService;

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/image/{id}/{title}/comments", method = RequestMethod.POST)
    public String addComments(@PathVariable("id") Integer id, @PathVariable("title") String title, @RequestParam(name = "comment")  String comment, HttpSession session) {
        Image image = imageService.getImage(id);
        User user = (User) session.getAttribute("loggeduser");
        Comment comment1 = new Comment();
        comment1.setCreatedDate(new Date());
        comment1.setImage(image);
        comment1.setText(comment);
        comment1.setUser(user);
        commentService.uploadComment(comment1);
        return "redirect:/images/{id}/{title}";

    }
}
