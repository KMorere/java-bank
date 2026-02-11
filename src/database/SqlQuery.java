package database;

public class SqlQuery {
    private String selection = "*"; // Par défaut, on prend tout
    private String table;
    private String condition = "";
    private String join = "";
    private String sort = "";

    public static class Builder {
        private final SqlQuery query = new SqlQuery();

        public Builder table(String table) {
            query.table = table;
            return this;
        }

        public Builder select(String fields) {
            query.selection = fields;
            return this;
        }

        public Builder filter(String condition) {
            query.condition = " WHERE " + condition;
            return this;
        }

        public Builder join(String table, String id1, String id2) {
            query.join += " JOIN " + table + " ON " + id1 + " = " + id2;
            return this;
        }

        public Builder sort(String column) {
            query.sort = " ORDER BY " + column;
            return this;
        }

        public String build() {
            return "SELECT " + query.selection + " FROM " + query.table + query.join + query.condition + query.sort + ";";
        }
    }

}
