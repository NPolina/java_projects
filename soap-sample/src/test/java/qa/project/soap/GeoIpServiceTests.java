package qa.project.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by user on 02.06.2016.
 */
public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("92.115.144.53");
        assertEquals(geoIP.getCountryCode(), "ROM");
    }

    @Test
    public void testInvalidIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("92.115.144.xxx");
        assertEquals(geoIP.getCountryCode(), "ROM");
    }
}
