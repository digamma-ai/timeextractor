

import java.io.IOException;
import java.util.List;

import com.codeminders.labs.timeextractor.business.CsvWriter;
import com.codeminders.labs.timeextractor.entities.Sort;
import com.codeminders.labs.timeextractor.entities.Tip;
import com.codeminders.labs.timeextractor.service.GetVenueTipsService;

public class MainTipsExtractor {

    public static void main(String[] args) throws IOException {

        String pathTofileToSave = "C:/nlp/set.csv";
        String oathToken = "XW4SVOI2KVA3PPWVWD22PVWEQZ3ZO0SWHRRPN2NVDQTYDQ35";
        String v = "20140721";

        GetVenueTipsService service = new GetVenueTipsService();
        CsvWriter writer = new CsvWriter();
        String[] venueIds = { "49d01698f964a520fd5a1fe3", "437e6b00f964a520b82a1fe3",
                "4ba294bdf964a520460638e3", "4bc2609474a9a5935d46d3f6", "4ba8b203f964a520a6e739e3",
                "4adcda09f964a520e83321e3", "4ac518d2f964a5203da720e3", "4c52b7a4b6dabe9a24d2bb13",
                "4ac518d2f964a52042a720e3", "4ac518d2f964a52044a720e3", "4ac518cdf964a520e9a520e3",
                "4af581a7f964a52076f921e3", "4af581a7f964a52076f921e3", "4af581a7f964a52076f921e3",
                "4ae2d9f8f964a5208d8f21e3", "4aec421af964a520b5c521e3", "4aec421af964a520b5c521e3",
                "4ac518d2f964a5203ca720e3", "4ac518d2f964a52040a720e3", "4ac518cdf964a520eca520e3",
                "4ac518cef964a520fca520e3", "4ac518d3f964a5204fa720e3", "4ac518d3f964a52078a720e3",
                "4ac518d3f964a52054a720e3", "4ac518d2f964a52045a720e3", "4ac518d3f964a5206fa720e3",
                "4ac518d3f964a52076a720e3", "4b1699f8f964a520c5ba23e3", "4adcda10f964a520af3521e3",
                "49f62947f964a520146c1fe3", "4ab52af0f964a520f27220e3", "44d344bef964a52041361fe3",
                "4698e043f964a520f0481fe3", "49cc413df964a5205a591fe3", "4585a93ef964a520ac3f1fe3",
                "44202b4ef964a52086311fe3", "4ba638e0f964a520d93c39e3", "4adcdab3f964a5203b5021e3",
                "4adcda10f964a5208b3521e3", "4adcda0af964a520623421e3", "4adcda10f964a5209a3521e3",
                "4bc74aab14d795218c1467e9", "4ed29485b634dd29941f8916", "4ad4f28ff964a52065fd20e3",
                "4ad4f28ff964a5204cfd20e3", "4a1063d9f964a520b5761fe3", "4a1fec4af964a5203c7c1fe3",
                "4a299b7df964a520b2951fe3", "4a0cfc37f964a52074751fe3", "4a451851f964a520c5a71fe3",
                "4ab14d7bf964a520f36820e3", "49e87228f964a5204c651fe3", "45c6c2bef964a5205e421fe3",
                "4750accef964a520b24c1fe3", "4a746fb2f964a52025de1fe3", "4297b480f964a52062241fe3" };

        for (String venueId : venueIds) {
            String json = service.getJsonAsString(venueId, Sort.POPULAR, oathToken, v, 500);
            List<Tip> tips = service.tipsForVenue(json, venueId);
            writer.writeCSVToFile(pathTofileToSave, tips);
        }
    }

}
