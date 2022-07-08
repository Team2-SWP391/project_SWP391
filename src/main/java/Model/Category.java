package Model;

public class Category {
        private int Cid;
        private String C_name;

        public int getCid() {
            return Cid;
        }

        public void setCid(int cid) {
            Cid = cid;
        }

        public String getC_name() {
            return C_name;
        }

        public void setC_name(String c_name) {
            C_name = c_name;
        }

        public Category(int cid, String c_name) {
            Cid = cid;
            C_name = c_name;
        }

        public Category() {
        }
}
