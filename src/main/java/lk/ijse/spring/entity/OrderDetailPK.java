package lk.ijse.spring.entity;

import lombok.*;

import javax.persistence.Embeddable;

@AllArgsConstructor
@ToString
@Embeddable
@Getter
@Setter
public class OrderDetailPK implements SuperEntity {
    private String orderId;
    private String itemCode;

    public OrderDetailPK() {
    }
}
