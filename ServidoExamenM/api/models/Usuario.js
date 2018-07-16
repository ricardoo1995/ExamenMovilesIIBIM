/**
 * Usuario.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    tipo:{type: "string"},
    username:{type: "string"},
    password:{type: "string"},

    pacienteId:{
     model: 'Paciente'

    },

    ordenes:{
      collection: 'Ordenes',
      via: 'usuarioId'
    }
  },

};

