package models;

public class Repair {
        Integer ServiceId;
        String Category;

        public Repair(Integer serviceId, String category) {
            this.ServiceId = serviceId;
            Category = category;
        }

        public Integer getServiceId() {
            return ServiceId;
        }

        public void setServiceId(Integer serviceId) {
            this.ServiceId = serviceId;
        }

        public String getCategory() {
            return Category;
        }

        public void setCategory(String category) {
            Category = category;
        }
    }
