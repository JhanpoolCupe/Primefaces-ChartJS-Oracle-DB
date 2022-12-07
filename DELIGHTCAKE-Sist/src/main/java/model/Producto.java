/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.List;
import lombok.Data;

@Data
public class Producto {

    private int IDPRO;
    private String NOMPRO;
    private Double PREPRO;
    private String DESPRO;
    private String ESTPRO;
    private int STOPRO = 1;
    private String IMGPRO;
    private int CANTVENDPRO;
    
    private int IDPROV;
    private String NOMPROV;
   
}
