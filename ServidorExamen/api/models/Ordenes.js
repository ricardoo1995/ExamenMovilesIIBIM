/**
 * Ordenes.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    fechaOrden: {
      type: 'string'
    },
    totalOrden: {
      type: 'number'
    },
    estadoOrden: {
      type: 'number'
    },
    latitud: {
      type: 'number'
    },
    longitud: {
      type: 'number'
    },
    costoDelivery: {
      type: 'number'
    },
    fechaEntrega: {
      type: 'string'
    },
    costoEntrega: {
      type: 'number'
    },
    detalleOrden: {
      collection: 'DetalleOrden',
      via: 'ordenesId'
    },

  },

};

