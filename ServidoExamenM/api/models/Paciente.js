/**
 * Paciente.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre: {
      type: 'string'
    },
    apellido: {
      type: 'string'
    },
    fechaNacimiento: {
      type: 'string'
    },
    numeroHijos: {
      type: 'number'
    },
    afiliado: {
      type: 'number'
    },
    contraseña: {
      type: 'string'
    },
    medicina: {
      collection: 'Medicina',
      via: 'pacienteId'
    },
    usuario:{
      collection: 'Usuario',
      via:'pacienteId'
    }
  },

};

