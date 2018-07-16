/**
 * Medicina.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    gramosAConsumir: {
      type: 'string'
    },
    nombre: {
      type: 'string'
    },
    numeroPastillas: {
      type: 'number'
    },
    composicion: {
      type: 'string'
    },
    fechaCaducidad: {
      type: 'string'
    },
    usadaPara: {
      type: 'string'
    },
    precio:{
      type: 'string'
    },
    estado:{
      type: 'number'
    },
    foto: {type:"string"},

    pacienteId: {
      model: 'Paciente'
    },
    fotos: {
      collection: 'Fotos',
      via: 'medicinaId'
    },
    detalleOrden: {
      collection: 'DetalleOrden',
      via: 'medicinaId'
    },
  },

};

