package Progress;

import java.io.IOException;
import java.util.List;

import Game.Game;

interface Backup {
    String saveCurrent() throws IOException;
    List<String> getSavesList();
    Game loadSave(String saveName) throws Exception;
}
