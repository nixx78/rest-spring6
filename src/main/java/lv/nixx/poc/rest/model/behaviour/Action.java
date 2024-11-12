package lv.nixx.poc.rest.model.behaviour;

public enum Action {

    ADD {
        @Override
        public String process(String data) {
            return "Processed:" +  data + ":" + this.name();
        }
    },
    UPDATE {
        @Override
        public String process(String data) {
            return "Processed:" + data + ":" + this.name();
        }
    },
    DELETE {
        @Override
        public String process(String data) {
            return "Processed:" + data + ":" + this.name();
        }
    };

    public abstract String process(String data);

}
