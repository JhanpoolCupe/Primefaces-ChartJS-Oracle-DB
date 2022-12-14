package controller;

import dao.ProductoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Producto;
import lombok.Data;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.component.export.PDFOrientationType;

@Data
@Named(value = "ProductoC")
@SessionScoped
public class ProductoC implements Serializable {

    private Producto producto;
    private ProductoImpl dao;
    private List<Producto> lstProducto;
    private List<Producto> lstProductoIMG;
    private int tipo = 1;
    private PDFOptions pdf;
    private ExcelOptions xls;

    public ProductoC() {
        producto = new Producto();
        dao = new ProductoImpl();
        lstProducto = new ArrayList<>();
        opcionesPersonalizacion();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en registar ProductoC/registar: ", e.getMessage());
        } finally {

        }

    }

    public void modificar() throws Exception {
        try {
            dao.modificar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Registrado con éxito"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en modificar ProductoC/modificar: ", e.getMessage());
        } finally {

        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Correcto", "Producto eliminado"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "\"Error en cambiar estado ProductoC/estado: ", e.getMessage());
        } finally {

        }
    }

    public void restaurar() throws Exception {
        try {
            dao.restaurar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Restaurado", "Restaurado con éxito"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "\"Error en restaurar ProductoC/restaurar: ", e.getMessage());
        } finally {

        }
    }


    public void listar() {
        try {
            lstProducto = dao.listarTodos(tipo);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "\"Error en eliminar ProductoC/modificar: ", e.getMessage());
        } finally {

        }
    }

    public void listarIMG() {
        try {
            lstProductoIMG = dao.listarTodosIMG();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "\"Error en eliminar ProductoC/modificar: ", e.getMessage());
        } finally {

        }
    }

    public void limpiar() {
        producto = new Producto();
    }

    public void opcionesPersonalizacion() {
        xls = new ExcelOptions();
        xls.setFacetBgColor("#19C7FF");
        xls.setFacetFontSize("10");
        xls.setFacetFontColor("#FFFFFF");
        xls.setFacetFontStyle("BOLD");
        xls.setCellFontColor("#000000");
        xls.setCellFontSize("8");
        xls.setFontName("Verdana");

        pdf = new PDFOptions();
        pdf.setFacetBgColor("#19C7FF");
        pdf.setFacetFontColor("#000000");
        pdf.setFacetFontStyle("BOLD");
        pdf.setCellFontSize("12");
        pdf.setFontName("Courier");
        pdf.setOrientation(PDFOrientationType.LANDSCAPE);
    }

    @PostConstruct
    public void construir() {
        listar();
    }
}
