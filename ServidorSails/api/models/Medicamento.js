/**
 * Medicamento.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    gramosAIngerir:{
      type:"number"
    },
    nombre:{
      type:"string"
    },
    composicion:{
      type:"string"
    },
   usadoPara:{
      type:"string"
    },
    fechaCaducidad:{
      type:"string"
    },
    numeroPastillas:{
      type:"number"
    },

    pacienteId:{
      type:"number"
    }
  },

};

