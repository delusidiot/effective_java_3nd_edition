package chapter2.Item_2;

import java.util.Objects;

public class Calzone extends Pizza {
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false;

        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        @Override
        Pizza build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }
}
