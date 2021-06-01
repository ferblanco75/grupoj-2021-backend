package ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.Critic;

public class CriticDTO {

        private Integer sourceId;
        private String userId;

        protected CriticDTO(){}

        public String getUserId() {
            return userId;
        }

        public Integer getSourceId() {
            return sourceId;
        }

        public CriticDTO(Integer sourceId, String userId){
            this.sourceId=sourceId;
            this.userId=userId;
        }

        public static CriticDTO fromModel(Critic critic){
            return new CriticDTO(critic.getSource().getId(),critic.getUserId());
        }

}
