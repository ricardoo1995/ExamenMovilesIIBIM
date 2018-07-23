/**
 * Paciente.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

   nombres: {
     type:"string"
   },
    apellidos:{
      type:"string"
    },
    fechaNacimiento:{
      type: "string"
    },
    numHijos:{
      type: "number"
    },
    tieneSeguro:{
      type:"number"
    }


  },

};

