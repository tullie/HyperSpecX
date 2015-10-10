package MineralClassification;

import MineralParsing.MineralDataI;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MineralDataToJsonTest {

  @Test public void testParseResolvedMineralData() throws Exception {
    Map<String, Double> mockMinerals = new HashMap<>();
    mockMinerals.put("Quartz", 0.5);
    mockMinerals.put("Bronium", 0.1);

    ResolvedMineralDatumI mockDatum = mock(ResolvedMineralDatumI.class);
    when(mockDatum.getRed()).thenReturn(1);
    when(mockDatum.getGreen()).thenReturn(2);
    when(mockDatum.getBlue()).thenReturn(3);
    when(mockDatum.getMinerals()).thenReturn(mockMinerals);

    List<ResolvedMineralDatumI> mockDatumRow = new ArrayList<>();
    mockDatumRow.add(mockDatum);
    List<List<ResolvedMineralDatumI>> mockDatumRect = new ArrayList<>();
    mockDatumRect.add(mockDatumRow);
    MineralDataI mockMineralData = mock(MineralDataI.class);
    when(mockMineralData.getResolvedMineralRect()).thenReturn(mockDatumRect);

    String expected = "[{\"r\":1,\"b\":3,\"g\":2,\"m\":{\"Bronium\":0.1,\"Quartz\":0.5}}]";
    assertThat(MineralDataToJson.parseResolvedMineralData(mockMineralData), is(expected));
  }
}
