package managers;

import models.Dragon;

import java.util.List;
import java.util.TreeMap;

public interface Storage {
    void writeCollection (TreeMap<Long, Dragon> collection, String filename);
    List<Dragon> readCollection (String filename);
}
