package markovModel.parameters;

public class PrefixKey {
    private String prefix1;
    private String prefix2;

    public PrefixKey(String prefix1, String prefix2) {
        this.prefix1 = prefix1;
        this.prefix2 = prefix2;
    }

    public PrefixKey(String prefix2) {
        this.prefix1 = "";
        this.prefix2 = prefix2;
    }

    public PrefixKey() {
        this.prefix1 = "";
        this.prefix2 = "";
    }

    public String getPrefix1() {
        return prefix1;
    }

    public void setPrefix1(String prefix1) {
        this.prefix1 = prefix1;
    }

    public String getPrefix2() {
        return prefix2;
    }

    public void setPrefix2(String prefix2) {
        this.prefix2 = prefix2;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof PrefixKey))
            return false;

        PrefixKey pk = (PrefixKey) obj;

        return pk.getPrefix1().equals(this.getPrefix1()) &&
                pk.getPrefix2().equals(this.getPrefix2());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + prefix1.hashCode();
        result = 31 * result + prefix2.hashCode();
        return result;
    }
}
