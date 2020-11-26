package com.nrodrigoc.logisticsapp.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaminhoneiroFrete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,  generator = "caminhoneiro_frete_seq")
    private Integer id;

    private Integer caminhoneiroId;

    private Integer freteId;



}
