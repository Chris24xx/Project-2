package dev.project2.API;

import dev.project2.DAO.Abstract.MediaDAO;
import dev.project2.DAO.Implementation.ContactUsDAOImp;
import dev.project2.DAO.Implementation.MediaDAOImp;
import dev.project2.DAO.Implementation.ReviewDaoImp;
import dev.project2.Service.Abstract.MediaService;
import dev.project2.Service.Implementation.ContactUsServiceImp;
import dev.project2.Service.Implementation.MediaServiceImp;
import dev.project2.Service.Implementation.ReviewImp;
import dev.project2.controllers.ContactUsController;
import dev.project2.controllers.MediaController;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        });

        // REVIEW
        ReviewDaoImp reviewDaoImp = new ReviewDaoImp();
        ReviewImp reviewImp = new ReviewImp(reviewDaoImp);
        ReviewController reviewController = new ReviewController(reviewImp);

        // CONTACT US
        ContactUsDAOImp contactDaoImp = new ContactUsDAOImp();
        ContactUsServiceImp contactService = new ContactUsServiceImp(contactDaoImp);
        ContactUsController contactController = new ContactUsController(contactService);

        // MEDIA
        MediaDAO mediaDAO = new MediaDAOImp();
        MediaService mediaService = new MediaServiceImp(mediaDAO);
        MediaController mediaController = new MediaController(mediaService);

        // WEB USER



        // REVIEW
        app.post("/review", reviewController.createReview);
        app.get("/review/{reviewId}/{userId}", reviewController.getReview);
        app.get("/review/all/{userId}", reviewController.getAllReview);
        app.get("/review/pending", reviewController.getPendingReview);
        app.get("/review/past", reviewController.getPastReviews);
        app.patch("/review/update/{reviewId}",reviewController.updateReview);
        app.delete("/review/delete/{reviewId}", reviewController.deleteReview);


        // CONTACT US
        app.post("/contact", contactController.createContactRequest);
        app.get("/contact/{contactId}", contactController.getContactRequestById);
        app.get("/contactRequests/all", contactController.getAllContactRequests);
        app.get("/contactRequests/pending", contactController.getPendingContactRequests);
        app.get("/contactRequests/completed", contactController.getCompletedContactRequests);
        app.patch("/updateContactRequestStatus/{contactId}", contactController.updateContactRequestById);
        app.delete("/deleteContactRequest/{contactId}", contactController.deleteContactRequestById);


        // MEDIA
        app.post("/newMedia", mediaController.createMedia);
        app.get("/books", mediaController.getAllBooks);
        app.get("/movies", mediaController.getAllMovies);
        app.get("/games", mediaController.getAllGames);
        app.get("/title/{title}", mediaController.getMediaByTitle);
        app.get("/pendingMedia", mediaController.getPendingMedia);
        app.get("/approvedMedia", mediaController.getApprovedMedia);
        app.patch("/approveMedia/{mediaId}", mediaController.approveMedia);
        app.delete("/deleteMedia/{mediaId}", mediaController.deleteMedia);


        // WEB USER




        app.start();
    }
}
