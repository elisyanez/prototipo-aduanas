package com.aduxpress.prototipo.controller;

import com.aduxpress.prototipo.model.Historial;
import com.aduxpress.prototipo.model.Usuario;
import com.aduxpress.prototipo.service.HistorialService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class InformeController {

    @Autowired
    private HistorialService historialService;

    @GetMapping("/informes")
    public String mostrarInformes(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(required = false) boolean todos,
            HttpSession session,
            Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        List<Historial> datos = null;
        if (fechaInicio != null && fechaFin != null) {
            if (usuario.getRol().equals("ADMIN") && todos) {
                datos = historialService.obtenerHistorialPorRango(fechaInicio.atStartOfDay(), fechaFin.plusDays(1).atStartOfDay());
            } else {
                datos = historialService.obtenerHistorialPorUsuarioYRango(usuario, fechaInicio.atStartOfDay(), fechaFin.plusDays(1).atStartOfDay());
            }
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            model.addAttribute("todos", todos);
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("datos", datos);
        return "informes";
    }

    @GetMapping("/informes/pdf")
    public void descargarPdf(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(required = false) boolean todos,
            HttpSession session,
            HttpServletResponse response) throws IOException {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Historial> datos;
        if (usuario.getRol().equals("ADMIN") && todos) {
            datos = historialService.obtenerHistorialPorRango(fechaInicio.atStartOfDay(), fechaFin.plusDays(1).atStartOfDay());
        } else {
            datos = historialService.obtenerHistorialPorUsuarioYRango(
                    usuario, fechaInicio.atStartOfDay(), fechaFin.plusDays(1).atStartOfDay());
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=historial.pdf");

        com.lowagie.text.Document document = new com.lowagie.text.Document();
        com.lowagie.text.pdf.PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        document.add(new com.lowagie.text.Paragraph("Historial de usuario"));
        document.add(new com.lowagie.text.Paragraph("Rango: " + fechaInicio + " a " + fechaFin));
        document.add(new com.lowagie.text.Paragraph(" "));

        int columnas = (usuario.getRol().equals("ADMIN") && todos) ? 4 : 3;
        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(columnas);
        if (columnas == 4) table.addCell("Usuario");
        table.addCell("Fecha");
        table.addCell("Acción");
        table.addCell("Detalle");
        for (Historial h : datos) {
            if (columnas == 4) table.addCell(h.getUsuario().getUsername());
            table.addCell(h.getFecha().toString());
            table.addCell(h.getAccion());
            table.addCell(h.getDetalle() != null ? h.getDetalle() : "");
        }
        document.add(table);
        document.close();
    }

    @GetMapping("/informes/excel")
    public void descargarExcel(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(required = false) boolean todos,
            HttpSession session,
            HttpServletResponse response) throws IOException {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Historial> datos;
        if (usuario.getRol().equals("ADMIN") && todos) {
            datos = historialService.obtenerHistorialPorRango(fechaInicio.atStartOfDay(), fechaFin.plusDays(1).atStartOfDay());
        } else {
            datos = historialService.obtenerHistorialPorUsuarioYRango(
                    usuario, fechaInicio.atStartOfDay(), fechaFin.plusDays(1).atStartOfDay());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=historial.xlsx");

        org.apache.poi.ss.usermodel.Workbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Historial");

        org.apache.poi.ss.usermodel.Row header = sheet.createRow(0);
        int col = 0;
        if (usuario.getRol().equals("ADMIN") && todos) {
            header.createCell(col++).setCellValue("Usuario");
        }
        header.createCell(col++).setCellValue("Fecha");
        header.createCell(col++).setCellValue("Acción");
        header.createCell(col++).setCellValue("Detalle");

        int rowIdx = 1;
        for (Historial h : datos) {
            org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowIdx++);
            int c = 0;
            if (usuario.getRol().equals("ADMIN") && todos) {
                row.createCell(c++).setCellValue(h.getUsuario().getUsername());
            }
            row.createCell(c++).setCellValue(h.getFecha().toString());
            row.createCell(c++).setCellValue(h.getAccion());
            row.createCell(c++).setCellValue(h.getDetalle() != null ? h.getDetalle() : "");
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
