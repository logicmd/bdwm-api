package us.hk.bdwm.api.core;

import javax.annotation.Resource;

public class Post {

    public static final String postUrlPrefix = "http://www.bdwm.net/bbs/bbscon.php";

    private String board;

    private String file;

    private String num;

    private int attach;

    private int dig;

    private String content;

    public Post(String board, String file, String num, int attach, int dig) {
        this.board = board;
        this.file = file;
        this.num = num;
        this.attach = attach;
        this.dig = dig;
        getContent();
    }

    public String getBoard() {
        return board;
    }

    public String getFile() {
        return file;
    }

    public String getNum() {
        return num;
    }

    public int getAttach() {
        return attach;
    }

    public int getDig() {
        return dig;
    }

    public String getContent() {
        if (content == null)
            content = postUrlPrefix + "?board=" + board + "&file=" + file + "&num=" + num + "&dig=" + dig;
        return content;
    }
}
