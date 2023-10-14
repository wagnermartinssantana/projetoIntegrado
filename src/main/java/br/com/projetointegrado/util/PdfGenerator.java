package br.com.projetointegrado.util;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.projetointegrado.model.entity.TransacaoFinanceira;
public class PdfGenerator {

    public byte[] generatePdf(List<TransacaoFinanceira> transacoes) throws DocumentException {

        Document document = new Document(PageSize.A4.rotate());
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, baos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HeaderFooterPageEvent  headerAndFooterGenerate = new HeaderFooterPageEvent();
        writer.setPageEvent(headerAndFooterGenerate);

        document.open();

        BaseColor corClaraBase = new BaseColor(240, 240, 240);

        PdfPTable tabelaEspacoVazio = new PdfPTable(1);
        tabelaEspacoVazio.setWidthPercentage(100);
        tabelaEspacoVazio.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        PdfPCell celulaEspacoVazio = new PdfPCell();
        celulaEspacoVazio.setFixedHeight(30);
        celulaEspacoVazio.setBorder(Rectangle.NO_BORDER);
        tabelaEspacoVazio.addCell(celulaEspacoVazio);

        document.add(tabelaEspacoVazio);

        Paragraph titulo = new Paragraph("CONSULTA TRANSAÇÃO FINANCEIRA", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(15);
        document.add(titulo);

        PdfPTable tabelaEspacoVazio2 = new PdfPTable(1);
        tabelaEspacoVazio2.setWidthPercentage(100);
        tabelaEspacoVazio2.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        PdfPCell celulaEspacoVazio2 = new PdfPCell();
        celulaEspacoVazio2.setFixedHeight(10);
        celulaEspacoVazio2.setBorder(Rectangle.NO_BORDER);
        tabelaEspacoVazio2.addCell(celulaEspacoVazio2);

        document.add(tabelaEspacoVazio2);

        float larguraDisponivel = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - (5 * 5);
        PdfPTable table = new PdfPTable(new float[] { 1f, 1f, 1f});

        table.setWidthPercentage(80);
        table.setTotalWidth(larguraDisponivel);
        table.setLockedWidth(true);

        PdfPCell cell = new PdfPCell(new Paragraph("TIPO", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        cell.setPaddingBottom(5);
        cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        cell.setBackgroundColor(corClaraBase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("VALOR", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        cell.setBackgroundColor(corClaraBase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("DATA", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        cell.setBackgroundColor(corClaraBase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setHeaderRows(1);

        boolean corClara = true;

        for (TransacaoFinanceira transacao : transacoes) {
        	
        	String tipoTransacao = transacao.getTipo() != null ? transacao.getTipo().toString() : "Tipo não especificado";
            PdfPCell celula1 = new PdfPCell(new Paragraph(tipoTransacao));
            PdfPCell celula2 = new PdfPCell(new Paragraph(String.valueOf(transacao.getValor())));
            PdfPCell celula3 = new PdfPCell(new Paragraph(String.valueOf(transacao.getData())));
            
            if (corClara) {
                celula1.setBackgroundColor(BaseColor.WHITE);
                celula2.setBackgroundColor(BaseColor.WHITE);
                celula3.setBackgroundColor(BaseColor.WHITE);
            } else {
                celula1.setBackgroundColor(corClaraBase);
                celula2.setBackgroundColor(corClaraBase);
                celula3.setBackgroundColor(corClaraBase);
            }
            
            celula1.setBorder(Rectangle.NO_BORDER);
            celula1.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula1.setPadding(5f);
            celula2.setBorder(Rectangle.NO_BORDER);
            celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula2.setPadding(5f);
            celula3.setBorder(Rectangle.NO_BORDER);
            celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula3.setPadding(5f);
            
            table.addCell(celula1);
            table.addCell(celula2);
            table.addCell(celula3);
            
            corClara = !corClara;
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }
}