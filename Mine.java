public class Mine {
    private boolean isMine;
    private boolean isSelected;
    private String symbol;

    public Mine(boolean isMine, boolean isSelected) {
        this.isMine = isMine;
        this.isSelected = isSelected;
    }

    public String setSymbol(String n) {
        this.symbol = n;
        return symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setMine(boolean mine) {
        isMine = mine;
        if(isMine){
            setSymbol("*");
        }
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
