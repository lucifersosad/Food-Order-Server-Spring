package spring.api.uteating.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.api.uteating.entity.Product;
import spring.api.uteating.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {
    private String commentId;
    private String commentDetail;
    private float rating;
    private String publisherId;
}
