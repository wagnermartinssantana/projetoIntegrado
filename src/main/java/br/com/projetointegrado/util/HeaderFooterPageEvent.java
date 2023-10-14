package br.com.projetointegrado.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

    Image imagem = null;

    public void onOpenDocument(PdfWriter pdfWriter, Document document) {
        try {
            imagem = Image.getInstance("src/main/resources/templates/imagens/icone.png");
            imagem.scaleToFit(100, 100);
            imagem.setAbsolutePosition(36, document.getPageSize().getHeight() - imagem.getScaledHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartPage(PdfWriter pdfWriter, Document document) {
        try {
            PdfContentByte cb = pdfWriter.getDirectContent();
            cb.addImage(imagem);

            float imagemPosY = document.getPageSize().getHeight() - 45;

            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDate dataAtual = LocalDate.now();
            LocalTime horaAtual = LocalTime.now();
            String dataAtualFormatada = dataAtual.format(formatterData);
            String horaAtualFormatada = horaAtual.format(formatterHora);

            PdfPTable tabelaParagrafos = new PdfPTable(1);
            tabelaParagrafos.setTotalWidth(200);
            tabelaParagrafos.setLockedWidth(true);

            Paragraph data = new Paragraph("Emissão: " + dataAtualFormatada + "     ");
            Paragraph hora = new Paragraph("Horário: " + horaAtualFormatada + "     ");

            data.setAlignment(Element.ALIGN_RIGHT);
            hora.setAlignment(Element.ALIGN_RIGHT);

            PdfPCell cellData = new PdfPCell(data);
            PdfPCell cellHora = new PdfPCell(hora);
            cellData.setBorder(Rectangle.NO_BORDER);
            cellHora.setBorder(Rectangle.NO_BORDER);

            tabelaParagrafos.addCell(cellData);
            tabelaParagrafos.addCell(cellHora);

            float tabelaPosX = document.getPageSize().getWidth() - tabelaParagrafos.getTotalWidth() + 60;
            float tabelaPosY = imagemPosY;

            tabelaParagrafos.writeSelectedRows(0, -1, tabelaPosX, tabelaPosY, cb);

            if (document.getPageNumber() > 1) {
                PdfPTable tabelaEspacoVazio = new PdfPTable(1);
                tabelaEspacoVazio.setWidthPercentage(100);
                tabelaEspacoVazio.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                PdfPCell celulaEspacoVazio = new PdfPCell();
                celulaEspacoVazio.setFixedHeight(45);
                celulaEspacoVazio.setBorder(Rectangle.NO_BORDER);
                tabelaEspacoVazio.addCell(celulaEspacoVazio);

                document.add(tabelaEspacoVazio);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void onEndPage(PdfWriter pdfWriter, Document document) {
        Rectangle rect = document.getPageSize();

        Phrase footer = new Phrase("Gestão Glamour");

        ColumnText.showTextAligned(pdfWriter.getDirectContent(),
                Element.ALIGN_CENTER, footer,
                (rect.getRight() / 2) - 10, rect.getBottom() + 15, 0);

        int pageNumber = pdfWriter.getCurrentPageNumber();
        int totalPages = pdfWriter.getPageNumber();

        Phrase page = new Phrase("Página " + pageNumber + "/" + totalPages);

        ColumnText.showTextAligned(pdfWriter.getDirectContent(),
                Element.ALIGN_CENTER, page,
                rect.getRight() - 35, rect.getBottom() + 15, 0);
    }
}