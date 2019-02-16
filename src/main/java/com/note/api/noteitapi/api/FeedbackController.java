package com.note.api.noteitapi.api;

import com.note.api.noteitapi.api.viewmodel.FeedbackViewModel;
import com.note.api.noteitapi.mail.FeedbackSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FeedbackController {

    @Autowired
    private FeedbackSender feedbackSender;

    @PostMapping("/feedback")
    public void sendFeedback(@RequestBody FeedbackViewModel feedbackViewModel, BindingResult bindingResult) throws ValidationException{
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Feedback has Errors. Cannot Send with Errors;");
        }

        this.feedbackSender.sendFeedback(feedbackViewModel.getEmail(), feedbackViewModel.getName(), feedbackViewModel.getFeedback());
    }


}
