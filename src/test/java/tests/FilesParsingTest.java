package tests;

import org.junit.jupiter.api.Test;
import tests.model.Glossary;
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Arsentiy Vavilov
 */
public class FilesParsingTest {
    private ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void csvFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("zipper.zip")
        )) {
            ZipEntry entry;
            boolean csvFound = false;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis, StandardCharsets
                            .UTF_8));
                    List<String[]> data = csvReader.readAll();

                    assertThat(data.get(0)).isEqualTo(new String[] {"booker12;9012;Rachel;Booker"});
                    assertThat(data.get(1)).isEqualTo(new String[] {"grey07;2070;Laura;Grey"});
                    assertThat(data.get(2)).isEqualTo(new String[] {"johnson81;4081;Craig;Johnson"});
                    assertThat(data.get(3)).isEqualTo(new String[] {"jenkins46;9346;Mary;Jenkins"});
                    assertThat(data.get(4)).isEqualTo(new String[] {"smith79;5079;Jamie;Smith"});

                    zis.closeEntry();
                    csvFound = true;
                    break;
                }
            }
            if (!csvFound) {
                throw new RuntimeException("CSV file not found in the archive!");
            }
        }
    }

    @Test
    void xlsxFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(requireNonNull(cl.getResourceAsStream("zipper.zip")))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {

                    XLS xls = new XLS(zis);
                    String value = xls.excel.getSheetAt(0).getRow(1).getCell(0)
                            .getStringCellValue();
                    assertThat(value).isEqualTo("Сделать дату действия в ценах");

                    break;
                }
            }
        }
    }

    @Test
    void pdfFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("zipper.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {

                    PDF pdf = new PDF(zis);
                    assertThat("Philip Hutchison").isEqualTo(pdf.author);

                    break;
                }
            }
        }
    }

    @Test
    void jsonFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("glossary.json")) {
            ObjectMapper mapper = new ObjectMapper();
            Glossary glossary = mapper.readValue(is, Glossary.class);
            assertThat(glossary.getTitle()).isEqualTo("kukusik");
            assertThat(glossary.getId()).isEqualTo(12345);
            assertThat(glossary.getGlossary().getSortAs()).isEqualTo("kek");
            assertThat(glossary.getGlossary().getGlossTerm()).isEqualTo("Standard Generalized Markup Language");
            assertThat(glossary.getGlossary().getAcronym()).isEqualTo("SGML");
        }

    }

}
