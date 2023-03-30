package Progress;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import Main.Game;

interface Backup {
    String saveCurrent() throws IOException;
    List<String> getSavesList();
    Game loadSave(String saveName) throws Exception;
}
