-- TABLE language_type (
--   id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
--   type VARCHAR(255) UNIQUE NOT NULL,
--   description TEXT
-- );

INSERT language_type(id, type, description)
VALUES
(1, 'Indo-European Languages', 'This diverse and widespread language group includes most European languages as well as some from further East.'),
(2, 'Ural-Altaic Languages', 'A controversial language family. Experts do not agree on which language family Japanese belongs to. We included it here in the Ural-Altaic family, but some linguists think it belongs better in the Austronesian family whilst others think it canot be classified. Likewise, there is some disagreement on whether Turkish and Korean belong to this group as well.'),
(3, 'Afro-Asiatic Languages', 'These languages are spoken in North Africa and include the Berber languages spoken by desert nomads of the Sahara.'),
(4, 'Niger-Congo Languages', 'Most African languages belong to this group, which may be the largest in the world in terms of distinct languages.'),
(5, 'Sino Tibetan Languages', 'Like its name suggests, this family groups the languages of China and Tibet.'),
(6, 'Austronesian Languages', 'Mostly spoken on the islands of Southeast Asia and the Pacific, only a few Austronesian languages are spoken on mainland Asia.');
