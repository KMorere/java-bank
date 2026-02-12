package utils;

import custom.InvalidQueryException;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SqlQuery {
    private String selection = "*"; // Par défaut, on prend tout
    private String table;
    private String condition = "";
    private String join = "";
    private String sort = "";
    private String insert = "";

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

        public Builder insert(String[] fields, String[] values) {
            query.insert = "(" +
                    Arrays.stream(fields).map(String::valueOf).collect(Collectors.joining(", ")) + ")"
                    + " VALUES " + "(" +
                    Arrays.stream(values).map(String::valueOf).collect(Collectors.joining(", ")) + ")";
            return this;
        }

        public String build(QueryType type) {
            switch (type) {
                case SELECT:
                    return "SELECT " + query.selection + " FROM " +
                            query.table + query.join + query.condition + query.sort + ";";
                case INSERT:
                    return "INSERT INTO " + query.table + query.insert + ";";
                default:
                    throw new InvalidQueryException("Invalid query type : " + type);
            }
        }
    }

    public enum QueryType {
        SELECT,
        INSERT
    }
}
