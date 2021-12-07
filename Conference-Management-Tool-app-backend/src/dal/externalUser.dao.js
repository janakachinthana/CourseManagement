const DatabaseService = require('../service/database.service.js');

//define the collection name
const collectionName = 'ExternalUsers';
//creating the database collection
const ExternalUsers = new DatabaseService(collectionName);

//add an user to the collection
const addExternalUser = async ({ email, name, contactNo, password, type, status, imagePath }) => {
    return await ExternalUsers.save({ email, name, contactNo, password, type,  status, imagePath })
}

//update external user image path
const updateExternaluserImagePath = async (id, { imagePath }) => {
    return await ExternalUsers.update(id, { imagePath });
};

//get all external users
const getAllExternalUsers = async () => {
    return await ExternalUsers.findAll();
};
const getExternalUser = async id =>{
    return await ExternalUsers.findById(id);
};
const deleteExternalUser = async id =>{
    return await ExternalUsers.delete(id);
}
module.exports = {
    addExternalUser,
    updateExternaluserImagePath,
    getAllExternalUsers,
    getExternalUser,
    deleteExternalUser
}