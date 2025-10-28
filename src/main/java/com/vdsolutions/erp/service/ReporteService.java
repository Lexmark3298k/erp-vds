package com.vdsolutions.erp.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdsolutions.erp.model.Cliente;
import com.vdsolutions.erp.model.Producto;

@Service
public class ReporteService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    // Nota: Removí tecnicoService ya que no se usa en los reportes actuales

    public byte[] generarReporteClientes() throws IOException {
        List<Cliente> clientes = clienteService.obtenerTodosClientes();
        
        try (Workbook workbook = new XSSFWorkbook(); 
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            
            Sheet sheet = workbook.createSheet("Clientes");
            
            // Estilo para el header
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            
            // Crear header
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Nombre", "Tipo Documento", "Número Documento", "Email", "Contacto", "Dirección"};
            
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Llenar datos
            int rowNum = 1;
            for (Cliente cliente : clientes) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(cliente.getIdCliente());
                row.createCell(1).setCellValue(cliente.getNombre());
                row.createCell(2).setCellValue(cliente.getTipoDocumento().name());
                row.createCell(3).setCellValue(cliente.getNumeroDocumento());
                row.createCell(4).setCellValue(cliente.getEmail());
                row.createCell(5).setCellValue(cliente.getContacto());
                row.createCell(6).setCellValue(cliente.getDireccion());
            }
            
            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    public byte[] generarReporteInventario() throws IOException {
        List<Producto> productos = productoService.obtenerTodosProductos();
        
        try (Workbook workbook = new XSSFWorkbook(); 
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            
            Sheet sheet = workbook.createSheet("Inventario");
            
            // Estilo para el header
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            
            // Crear header
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Nombre", "Categoría", "Precio", "Stock", "Stock Mínimo", "Proveedor", "Estado"};
            
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Llenar datos
            int rowNum = 1;
            for (Producto producto : productos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(producto.getIdProducto());
                row.createCell(1).setCellValue(producto.getNombre());
                row.createCell(2).setCellValue(producto.getCategoria());
                row.createCell(3).setCellValue(producto.getPrecio().doubleValue());
                row.createCell(4).setCellValue(producto.getStock());
                row.createCell(5).setCellValue(producto.getStockMinimo());
                row.createCell(6).setCellValue(producto.getProveedor());
                row.createCell(7).setCellValue(producto.isBajoStock() ? "BAJO STOCK" : "NORMAL");
            }
            
            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    // Método adicional para reporte de técnicos (opcional para el futuro)
    public byte[] generarReporteTecnicos() throws IOException {
        // Implementación futura
        return new byte[0];
    }
}