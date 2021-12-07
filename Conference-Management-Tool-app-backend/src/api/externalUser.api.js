const externalUserDao = require('../dal/externalUser.dao');
const mineTypes = require('mime-types');
const fs = require('fs');
const path = require('path');

/* asestes and externaluser dir */

const assetDir = `${process.cwd()}${path.sep}public/assets`;
const externalUserDir = `${assetDir}${path.sep}externaluser`;

/** add external user */

const addExternalUser = async ({ email, name, contactNo, password, type,  status }, ctxExternalUserImage) => {
    const externalUser = {
        email,
        name,
        contactNo,
        password,
        type,
        status,
        imagePath: null
    }

    return new Promise(async (resolve, reject) => {
        try {
            const generateResult = await externalUserDao.addExternalUser(externalUser);

            /* check the admin has upload an image*/
            if (ctxExternalUserImage) {

                /** get image extension */
                const fileType = mineTypes.extension(ctxExternalUserImage?.type);

                /** set image save path */
                const desFilePath = `${externalUserDir + path.sep + generateResult.insertedId}.${fileType}`;
                const dbImagePath = `/assets/externaluser/${generateResult.insertedId}.${fileType}`;

                console.log("des path", desFilePath);

                console.log("db image path", dbImagePath);
                try {
                    fs.copyFileSync(ctxExternalUserImage?.path, desFilePath);
                    fs.unlinkSync(ctxExternalUserImage?.path);

                    try {
                        /** update the image path of external user`s */
                        const result = await externalUserDao.updateExternaluserImagePath(generateResult.insertedId,
                            { imagePath: dbImagePath });
                        resolve(generateResult);

                    } catch (error) {
                        /** error: when having issues in updating dbImagePath  */
                        reject(error);
                    }
                } catch (error) {
                    /** error when copping file */
                    reject(error);
                }
            } else {
                /** if admin not insert an images just only save the details */
                resolve(generateResult);
            }

        } catch (error) {
            reject(error);
            console.log("test")
        };
    });
};

/**get all external users */
const getExternalUsers = async () => {
    return await externalUserDao.getAllExternalUsers();
};

const getExternalUser = async id => {
    return await externalUserDao.getExternalUser(id);
};
const deleteExternalUser = async id=>{
    return new Promise(async (resolve, reject) =>{
        try {
           
            // const existingExternalUser = await externalUserDao.getExternalUser(id);
            // console.log(path);
            // const oldImageFilePath = `${externalUserDir + path.sep + path.parse(existingExternalUser?.imagePath).base}`;
           
            // if (fs.existsSync(oldImageFilePath)) {
            //     fs.unlinkSync(oldImageFilePath);
            // }
            const result = await externalUserDao.deleteExternalUser(id);
            resolve(result);
        } catch (error) {
            reject(error);
        }
    }) 
};


module.exports = {
    addExternalUser,
    getExternalUsers,
    getExternalUser,
    deleteExternalUser
}

