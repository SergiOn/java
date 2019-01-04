-- TABLE greeting (
--   id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
--   language VARCHAR(255) UNIQUE NOT NULL,
--   translation VARCHAR(255) NOT NULL,
--   description TEXT,
--   language_type_id BIGINT(20) NOT NULL,
--   FOREIGN KEY (language_type_id) REFERENCES language_type (id)
-- );

INSERT greeting(
  language,
  translation,
  description,
  language_type_id
)
VALUES
(
  'English',
  'Hello',
  'Where to say it: International language.',
  1
),
(
  'French',
  'Bonjour',
  'Where to say it: Apart from France, Belgium and Switzerland this will also be understood in Morocco, Tunisia and Algeria and the sub-Saharan African countries of DR Congo, Côte d’Ivoire, Cameroon, Guinea, Gabon and Mauritius.',
  1
),
(
  'Spanish',
  'Hola',
  'Where to say it: Outside Spain, Spanish, or Castillian as it is sometimes called, is the main language of all Central and South American countries apart from Brazil. It is also the second most common language in the USA, spoken by more than 34m Hispanic Americans.',
  1
),
(
  'German',
  'Hallo / Guten tag',
  'Where to say it: Germany, Austria and Switzerland',
  1
),
(
  'Italian',
  'Ciao',
  'Where to say it: Italy.',
  1
),
(
  'Portuguese',
  'Olà',
  'Where to say it: Portugal and Brazil. Also spoken in former Portuguese colonies of Angola, Mozambique, Cape Verde, São Tomé and Macau.',
  1
),
(
  'Hindi',
  'Namaste',
  'Where to say it: Northern India and Nepal. Hindi is one of the official languages of India, but is spoken as native language by only 41% of the population. Some people classify Hindi as the same language as Urdu, which is spoken in Pakistan.',
  1
),
(
  'Persian (Farsi)',
  'Salaam',
  'Where to say it: Iran, Afghanistan, Tajikistan. Also parts of Uzbekistan and Bahrain. NB. Persian is sometimes called Farsi. That’s the local name for Persian as it is spoken in Iran.',
  1
),
(
  'Russian',
  'Zdras-tvuy-te',
  'Where to say it: Russia, and as a first or second language in the Eastern European, Caucasian and Central Asian countries of the former USSR. Kazakhstan in particular has large numbers of ethnic Russians who speak Russian rather than Kazakh.',
  1
),
(
  'Ukraine',
  'Pruvit',
  'Where to say it: Ukraine',
  1
),
(
  'Japanese',
  'Ohayo / Konnichiwa / Konban wa',
  'Where to say it: Japanese is spoken pretty much only in Japan. The greetings above are used in the morning, around midday and in the evening respectively.',
  2
),
(
  'Korean',
  'Ahn-young-ha-se-yo',
  'Where to say it: North and South Korea.',
  2
),
(
  'Turkish',
  'Merhaba',
  'Where to say it: Turkish is spoken in Turkey and Cyprus. Also the languages spoken in Azerbeijan and parts of Iran, Georgia and the Balkans are very similar to Turkish.',
  2
),
(
  'Mongolian',
  'Sain bainuu',
  'Where to say it: Mongolia. Mongolian speakers also live in some parts of Russia, China (Inner Mongolia) and Kyrgyzstan.',
  2
),
(
  'Kazakh',
  'Salemetsiz be?',
  'Where to say it: Almost 7 million of the world’s 10 million Kazakh speakers live in Kazakhstan. The rest are divided between Xinxiang province in China, Uzbekistan, Russia, Mongolia, Turkmenistan, Ukraine and Tajikistan. As in Mandarin, the literal translation of this greeting is ‘how are you?’',
  2
),
(
  'Hungarian',
  'Szia',
  'Where to say it: Mostly in Hungary, although parts of Austria and the Balkans have Hungarian speakers.',
  2
),
(
  'Arabic',
  'Marhaba',
  'Where to say it: Arabic in various dialects is spoken throughout North Africa and the Middle East. It is a main language in the following countries: Algeria, Bahrain, Chad, Egypt, Eritrea, Iraq, Israel, Jordan, Kuwait, Lebanon, Libya, Mauritania, Morocco, Oman, Palestine, Qatar, Saudi Arabia, Somalia, Sudan, Syria, Tunisia, UAE, Western Sahara, Yemen.',
  3
),
(
  'Hausa',
  'Sannu / Salama aleikum',
  'Where to say it: Hausa is the native language of inhabitants of Niger and Northern Nigeria, but it is also used as lingua franca in many countries of West and Central Africa.',
  3
),
(
  'Swahili',
  'Jambo / Habari',
  'Where to say it: Swahili has between 5 and 10 million native speakers who mainly live in Tanzania, Uganda and Kenya. But it is used as a lingua franca for most of East Africa and second language speakers swell the ranks to a massive 80 million!',
  4
),
(
  'Mandarin',
  'Ni hau',
  'Where to say it: Mandarin is the most spoken language in the world – it is spoken by at least 50% of China’s 1.3bn population.',
  5
),
(
  'Cantonese (Yue)',
  'Nay hoh',
  'Where to say it: Southern China (especially Guangdong province), Hong Kong and Macau.',
  5
),
(
  'Bahasa Indonesia',
  'Halo',
  'Where to say it: Although there are over 300 different dialects spoken in Indonesia, Bahasa Indonesia is spoken by much of the population as a second language. It is also very similar to the Malay language of Malaysia.',
  6
);
