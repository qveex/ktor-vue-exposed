import axios from "axios";

export const collectionModule = {
    state: () => ({
        collections: []
    }),
    getters: {

    },
    mutations: {
        setCollections(state, collections) {
            state.collections = collections;
        }

    },
    actions: {
        async createCollection({state, commit, collection}) {
            if (collection.name === '' || collection.type === '' || collection.description === '')
                alert('Please, fill all fields')
            else {
                try {
                    await axios.post(state.baseURL, JSON.parse(JSON.stringify(collection)))
                    await this.fetchCollections(commit)
                } catch (e) {
                    alert(e.message)
                }
            }

        },
        async removeCollection({state, collection}) {
            try {
                await axios.delete(state.baseURL + '/' + collection.id,)
                await this.fetchCollections()
            } catch (e) {
                alert(e.message)
            }
        },
        async fetchCollections({state, commit}) {
            try {
                const response = await axios.get(this.baseURL)
                commit('setCollections', response.data)
            } catch (e) {
                alert(e.message)
            }
        }
    }
}