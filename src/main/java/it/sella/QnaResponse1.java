package it.sella;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.sella.model.UserDetail;

public class QnaResponse1 {
	private static final Logger logger = LoggerFactory.getLogger(QnaResponse1.class);

	public static String getJsonResponse(String senderId, String requestText, UserDetail userDetail) {
		String jsonResponse = "";		
		switch (getActualKeyword(requestText)) {
		case "hi":
			jsonResponse = String.format(
					"{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }",
					"Ciao " + userDetail.getFirstName() + " " + userDetail.getLastName()+",How can I help you?");
			break;		
		case "view product":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"button\", \"text\":\"Our Products!!!\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"CONTI\", \"payload\":\"list conti\" }, { \"type\":\"postback\", \"title\":\"CARTE\", \"payload\":\"list carte\" }, { \"type\":\"postback\", \"title\":\"View More\", \"payload\":\"view1\" } ] } } } }";
			break;
		case "view1":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"button\", \"text\":\"Other Products!!!\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"INVESTIMENTI E RISPARMIO\", \"payload\":\"list investimenti e risparmio\" }, { \"type\":\"postback\", \"title\":\"FINANZIAMENTI\", \"payload\":\"list finanziamenti\" }, { \"type\":\"postback\", \"title\":\"View More\", \"payload\":\"view2\" } ] } } } }";
			break;
		case "view2":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"button\", \"text\":\"Other Products !!!\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"PROTEZIONE\", \"payload\":\"list protezione\" }, { \"type\":\"postback\", \"title\":\"More\", \"payload\":\"view product\" } ] } } } }";
			break;
		case "list conti":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"CONTI\", \"image_url\":\"https://udayanbot.herokuapp.com/img/conti_correnti_bse.gif\", \"subtitle\":\"WEBSELLA\", \"default_action\":{ \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/conti-e-carte/websella.jsp\", \"webview_height_ratio\":\"compact\" }, \"buttons\":[{ \"type\":\"postback\", \"title\":\"Websella\", \"payload\":\"websella\" } ] } ] } } } }";
			break;
		case "list carte":
		    jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"button\", \"text\":\"Our Carte!!!\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"CARTE DI DEBITO\", \"payload\":\"all carte details\" }, { \"type\":\"postback\", \"title\":\"CARTE DI CREDITO\", \"payload\":\"all carte details\" }, { \"type\":\"postback\", \"title\":\"HYPE PLUS\", \"payload\":\"all carte details\" }  ] } } } }";
		break;
		case "list investimenti":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Consulenza Servizi investimento\", \"image_url\":\"https://udayanbot.herokuapp.com/img/consulenza_investimenti.png\", \"subtitle\":\"Investimenti\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"consulenza\" } ] }, { \"title\":\"Sella Evolution Gestioni Patrimoniali\", \"image_url\":\"https://udayanbot.herokuapp.com/img/sella_evolution.png\", \"subtitle\":\"Investimenti\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"evolution\" } ] }, { \"title\":\"Fondi d'investimento\", \"image_url\":\"https://udayanbot.herokuapp.com/img/fondi.png\", \"subtitle\":\"Investimenti\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"fondi\" } ] }, { \"title\":\"Investimenti PIR\", \"image_url\":\"https://udayanbot.herokuapp.com/img/investimenti_pir.png\", \"subtitle\":\"Investimenti\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"pir\" } ] } ] } } } }";
			break;
		case "websella":
			jsonResponse = " { \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"WEBSELLA\", \"image_url\":\"https://udayanbot.herokuapp.com/img/conti_correnti_bse.gif\", \"subtitle\":\"Canone mensile zero euro  0 Euro\\n Una carta di debito a zero euro\\n operatività 24h/24h \\n13h al giorno di assistenza\\n4 prelievi gratuiti al mese da tutti gli ATM UE\\n300 succursali\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/conti-e-carte/websella.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/onboarding/fe/03268/contowebsella/#/start/CONTO_WEBSELLA\", \"title\":\"Onboarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
			break;	
		case "carte di debito":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"CARTE DI DEBITO\", \"image_url\":\"https://udayanbot.herokuapp.com/img/cirrus_maestro_basic.jpg\", \"subtitle\":\"Ricevila a casa o ritirala in succursale\\n Saldo del c/c in tempo reale\\n Max sicurezza con Chip & PIN \\n Paghi e prelevi ovunque con il circuito Maestro \\n Accedi a promozioni dedicate \\n Scopri un mondo di servizi\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/conti-e-carte/carte/cirrus-maestro-basic.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/AuthenticationDelegatedServlet?delegated_service=219&SECODE=ONBOARDING_CL&SEACTION=LOGIN&SEPARAMS=parameterCode=CIRRUSBASIC\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
			break;			
		case "carte di credito":		
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"VISA CLASSIC\", \"image_url\":\"https://udayanbot.herokuapp.com/img/visa_classic_bse.gif\", \"subtitle\":\"Con Visa Classic puoi fare acquisti e prelievi in tutto il mondo, con la garanzia della massima sicurezza grazie alla tecnologia Chip & PIN e al servizio Verified by Visa.\\\\n Canone primo anno 31 €\\\\n Gratis oltre i 7000 € di spese/anno\\\\n Sicura con Chip & PIN e Verified by Visa\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/conti-e-carte/carte/visa-classic.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/AuthenticationDelegatedServlet?delegated_service=219&SECODE=ONBOARDING_CL&SEACTION=LOGIN&SEPARAMS=parameterCode=VISA_CLASSIC_BSE\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] },{ \"title\":\"MASTERCARD CLASSIC\", \"image_url\":\"https://udayanbot.herokuapp.com/img/mastercard_classic_bse.gif\", \"subtitle\":\"Mastercard Classic è la carta di credito che prevede, se utilizzata a saldo, un addebito unico mensile del tuo speso. E' adatta per la gestione della tua liquidità e ti permette di effettuare pagamenti, anche tramite la funzionalità contatless\\\\n Canone primo anno 31 € \\\\n Gratis oltre i 7000 € di spese/anno \\\\n Sicura con Chip & PIN e Mastercard SecureCode\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/conti-e-carte/carte/mastercard-classic.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/AuthenticationDelegatedServlet?delegated_service=219&SECODE=ONBOARDING_CL&SEACTION=LOGIN&SEPARAMS=parameterCode=MASTERCARD_CLASSIC\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
			break;
		case "visa classic":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"VISA CLASSIC\", \"image_url\":\"https://udayanbot.herokuapp.com/img/visa_classic_bse.gif\", \"subtitle\":\"Con Visa Classic puoi fare acquisti e prelievi in tutto il mondo, con la garanzia della massima sicurezza grazie alla tecnologia Chip & PIN e al servizio Verified by Visa.\\\\n Canone primo anno 31 €\\\\n Gratis oltre i 7000 € di spese/anno\\\\n Sicura con Chip & PIN e Verified by Visa\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/conti-e-carte/carte/visa-classic.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/AuthenticationDelegatedServlet?delegated_service=219&SECODE=ONBOARDING_CL&SEACTION=LOGIN&SEPARAMS=parameterCode=VISA_CLASSIC_BSE\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
			break;
		case "mastercard classic":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"MASTERCARD CLASSIC\", \"image_url\":\"https://udayanbot.herokuapp.com/img/mastercard_classic_bse.gif\", \"subtitle\":\"Mastercard Classic è la carta di credito che prevede, se utilizzata a saldo, un addebito unico mensile del tuo speso. E' adatta per la gestione della tua liquidità e ti permette di effettuare pagamenti, anche tramite la funzionalità contatless\\\\n Canone primo anno 31 € \\\\n Gratis oltre i 7000 € di spese/anno \\\\n Sicura con Chip & PIN e Mastercard SecureCode\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/conti-e-carte/carte/mastercard-classic.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/AuthenticationDelegatedServlet?delegated_service=219&SECODE=ONBOARDING_CL&SEACTION=LOGIN&SEPARAMS=parameterCode=MASTERCARD_CLASSIC\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
			break;
		case "hype plus":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"HYPE PLUS\", \"image_url\":\"https://udayanbot.herokuapp.com/img/hype_bse.gif\", \"subtitle\":\"HYPE Plus è semplice e smart: controlla tutto ciò che serve dal tuo smartphone\\n Puoi ricaricare la tua carta fino a 50.000€ all'anno\\n Prelievi gratuiti da tutti gli ATM in Italia e nel mondo\\n Visualizzi all'istante i movimenti della tua carta\\n La metti in pausa quando vuoi, direttamente dall'app\\n Hai il tuo IBAN attivo in pochi minuti\\n Puoi pagare con il tuo smartphone\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/conti-e-carte/carte/hype.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.hype.it/signup/request/hype/contact-info\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] }  ] } } } }";
			break;
		case "all carte details":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"list\", \"elements\":[ { \"title\":\"CARTE DI DEBITO\", \"image_url\":\"https://udayanbot.herokuapp.com/img/cirrus_maestro_basic.jpg\", \"subtitle\":\"CARTE\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"CARTE DI DEBITO\" } ] }, { \"title\":\"CARTE DI CREDITO\", \"image_url\":\"https://udayanbot.herokuapp.com/img/visa_classic_bse.gif\", \"subtitle\":\"VISA\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"CARTE DI CREDITO\" } ] },{ \"title\":\"CARTE DI CREDITO\", \"image_url\":\"https://udayanbot.herokuapp.com/img/mastercard_classic_bse.gif\", \"subtitle\":\"MASTER\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"CARTE DI CREDITO\" } ] }, { \"title\":\"HYPE PLUS\", \"image_url\":\"https://udayanbot.herokuapp.com/img/hype_bse.gif\", \"subtitle\":\"CARTE\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"HYPE PLUS\" } ] } ] } } } }";
			break;		
		case "cirrus maestro basic":
			jsonResponse = "{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"You are applying cirrus maestro basic \" } }";
			break;
		case "hypeplus application":
			jsonResponse = "{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"You are applying hypeplus application \" } }";
			break;
		case "welcome msg":
			jsonResponse =String.format("{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s, Welcome to Sella bot, How can I help you? \" } }","Hi "+userDetail.getFirstName()+" "+userDetail.getLastName());
			break;		
		case "consulenza":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Consulenza investimenti\", \"image_url\":\"https://udayanbot.herokuapp.com/img/consulenza_investimenti.png\", \"subtitle\":\"Il miglior modo che conosciamo per fare banca è dedicarci a te, completamente\\nUn professionista a tua disposizione\\nDedizione nel curare i tuoi interessi\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/investimenti-e-risparmio/consulenza-servizi-investimento.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/investimenti-e-risparmio/consulenza-servizi-investimento.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "evolution":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Sella Evolution Gestioni Patrimoniali\", \"image_url\":\"https://udayanbot.herokuapp.com/img/sella_evolution.png\", \"subtitle\":\"Per i tuoi risparmi scegli Sella Evolution,\\nil nuovo servizio pensato per raggiungere i tuoi obiettivi\\nAttraverso un questionario online definiamo il tuo profilo di investitore\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/investimenti-e-risparmio/gestione-patrimoniale.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/investimenti-e-risparmio/gestione-patrimoniale.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "fondi":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Fondi d'investimento\", \"image_url\":\"https://udayanbot.herokuapp.com/img/fondi.png\", \"subtitle\":\"L’eccellenza dei fondi sul mercato e le società di gestione che hanno dimostrato nel tempo la migliore strategia\\nPiù di 1800 fondi\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/investimenti-e-risparmio/fondi-sicav-pac.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/investimenti-e-risparmio/fondi-sicav-pac.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } } ";
		break;
		case "pir":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Investimenti PIR\", \"image_url\":\"https://udayanbot.herokuapp.com/img/investimenti_pir.png\", \"subtitle\":\"I Piani individuali di Risparmio\\nLimiti di investimento sui PIR\\nLa proposta di Banca Sella\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/investimenti-e-risparmio/pir.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/investimenti-e-risparmio/pir.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "list finanziamenti":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Prestito Sella.it\", \"image_url\":\"https://udayanbot.herokuapp.com/img/prestito_sella_it.png\", \"subtitle\":\"Finanziamenti\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"prestito sella.it\" } ] }, { \"title\":\"Mutui\", \"image_url\":\"https://udayanbot.herokuapp.com/img/mutui.png\", \"subtitle\":\"Finanziamenti\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"mutui\" } ] }, { \"title\":\"Prestito d’onore\", \"image_url\":\"https://udayanbot.herokuapp.com/img/loan_student.png\", \"subtitle\":\"Finanziamenti\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"prestito d'onore\" } ] }, { \"title\":\"Cessione del quinto\", \"image_url\":\"https://udayanbot.herokuapp.com/img/Cessione_del_quinto.png\", \"subtitle\":\"Finanziamenti\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"cessione del quinto\" } ] }, { \"title\":\"Prestidea Ambiente\", \"image_url\":\"https://udayanbot.herokuapp.com/img/prestidea_ambiente.png\", \"subtitle\":\"Finanziamenti\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"prestidea ambiente\" } ] } ] } } } }";
		break;
		case "prestito sella.it":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Prestito Sella.it\", \"image_url\":\"https://udayanbot.herokuapp.com/img/prestito_sella_it.png\", \"subtitle\":\"TAN fixed 5.90%   APR 6.14% *\\nExample calculated on the amount of € 10,000 in 72 monthly installments of € 165.26 per month.\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/finanziamenti/prestito-sella-it.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/finanziamenti/prestito-sella-it.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "mutui":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Mutui\", \"image_url\":\"https://udayanbot.herokuapp.com/img/mutui.png\", \"subtitle\":\"MUTUO A TASSO FISSO\\nMUTUO A TASSO VARIABILE\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/finanziamenti/mutui.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/finanziamenti/mutui.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "prestito d'onore":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Prestito d’onore\", \"image_url\":\"https://udayanbot.herokuapp.com/img/loan_student.png\", \"subtitle\":\"Il prestito d'onore è un particolare finanziamento per gli studenti \\nche vogliono specializzare la propria formazione ed \\nentrare con maggiori competenze nel mondo del lavoro.\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/finanziamenti/prestito-onore.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/finanziamenti/prestito-onore.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "cessione del quinto":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Cessione del quinto\", \"image_url\":\"https://udayanbot.herokuapp.com/img/Cessione_del_quinto.png\", \"subtitle\":\"La cessione del quinto della pensione è un prestito a tasso fisso che può avere importo massimo di 45.000 euro e durata compresa tra 36 e 120 mesi. Non è necessario motivare la richiesta per il suo ottenimento. L’importo della rata non può essere superiore a 1/5 del valore della pensione.\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/finanziamenti/cessione-del-quinto.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/finanziamenti/cessione-del-quinto.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "prestidea ambiente":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Prestidea Ambiente\", \"image_url\":\"https://udayanbot.herokuapp.com/img/prestidea_ambiente.png\", \"subtitle\":\"Un consumo consapevole aiuta a conservare e rigenerare le risorse energetiche, per questo da sempre Banca Sella adotta comportamenti sostenibili che valorizzano le risorse naturali.\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/finanziamenti/prestidea-ambiente.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/finanziamenti/prestidea-ambiente.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "list protezione":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Protezione Persona\", \"image_url\":\"https://udayanbot.herokuapp.com/img/persona.png\", \"subtitle\":\"PROTEZIONE\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"persona\" } ] }, { \"title\":\"Protezione Famiglia\", \"image_url\":\"https://udayanbot.herokuapp.com/img/family.png\", \"subtitle\":\"PROTEZIONE\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"famiglia\" } ] }, { \"title\":\"Protezione Casa\", \"image_url\":\"https://udayanbot.herokuapp.com/img/casa.png\", \"subtitle\":\"PROTEZIONE\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"casa\" } ] }, { \"title\":\"Assicurazione Valore Auto\", \"image_url\":\"https://udayanbot.herokuapp.com/img/auto.png\", \"subtitle\":\"PROTEZIONE\", \"buttons\":[ { \"type\":\"postback\", \"title\":\"View\", \"payload\":\"assicurazione valore auto\" } ] } ] } } } }";
		break;
		case "persona":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Protezione Persona\", \"image_url\":\"https://udayanbot.herokuapp.com/img/persona.png\", \"subtitle\":\"rimborso spese mediche;\\nricovero ospedaliero, convalescenza e gesso\\ninvalidità permanente;\\ndecesso a seguito di infortunio.\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/protezione/protezione-infortuni.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/protezione/protezione-infortuni.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "auto":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Assicurazione Valore Auto\", \"image_url\":\"https://udayanbot.herokuapp.com/img/auto.png\", \"subtitle\":\"\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/protezione/protezione-auto.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/protezione/protezione-auto.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "casa":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Protezione Casa\", \"image_url\":\"https://udayanbot.herokuapp.com/img/casa.png\", \"subtitle\":\"Casa InChiaro prodotto modulare che può proteggere in caso di:\\ndanni all'abitazione e al contenuto, con tutte le coperture associate alla garanzia incendio;\\nfurto, ma anche rapina o scippo ai danni dei componenti del nucleo familiare;\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/protezione/protezione-casa.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/protezione/protezione-casa.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "famiglia":
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Protezione Famiglia\", \"image_url\":\"https://udayanbot.herokuapp.com/img/family.png\", \"subtitle\":\"Con Famiglia InChiaro:\\nLe prestazioni assicurate sono:\\nRC del capo famiglia.\\ntutela legale (attiva e passiva).\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/protezione/protezione-famiglia.jsp\", \"title\":\"For More Details\" }, { \"type\":\"web_url\", \"url\":\"https://www.sella.it/banca-online/privati/protezione/protezione-famiglia.jsp\", \"title\":\"On Boarding\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] } ] } } } }";
		break;
		case "call our representative":
			jsonResponse = String.format(
					"{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }",
					"Hi " + userDetail.getFirstName() + " " + userDetail.getLastName()+", puoi contattare l'assistenza clienti al numero 800.142.142 (per le chiamate da rete fissa nazionale) oppure al numero 0039.015.24.34.617 (per le chiamate dall'estero e da telefono cellulare) attivi dal luneda al venerda dalle ore 8.00 alle 21.00. Hai bisogno di altre informazioni?");
			break;
		case "bye":
		case "":
			jsonResponse = String.format(
					"{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }",
					"Grazie " + userDetail.getFirstName() + " " + userDetail.getLastName()+", Speriamo di aver risposto alle tue domamde.  Buona giornata ");
		break;
		case "test1":			
			jsonResponse="{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\": { \"attachment\": { \"type\": \"template\", \"payload\": { \"template_type\": \"list\", \"top_element_style\": \"compact\", \"elements\": [ { \"title\": \"Classic T-Shirt Collection\", \"subtitle\": \"See all our colors\", \"image_url\": \"https://udayanbot.herokuapp.com/img/hype_bse.gif\", \"buttons\": [ { \"title\": \"View\", \"type\": \"web_url\", \"url\": \"https://www.sella.it/banca-online/privati/conti-e-carte/carte/cirrus-maestro-basic.jsp\", \"messenger_extensions\": true, \"webview_height_ratio\": \"tall\", \"fallback_url\": \"https://www.sella.it/banca-online/privati/index.jsp\" } ] }, { \"title\": \"Classic Wgute T-Shirt\", \"subtitle\": \"See all other colors\", \"default_action\": { \"type\": \"web_url\", \"url\": \"https://www.sella.it/banca-online/privati/index.jsp\", \"messenger_extensions\": false, \"webview_height_ratio\": \"tall\" } }, { \"title\": \"Classic Blue T-Shirt\", \"image_url\": \"https://udayanbot.herokuapp.com/img/hype_bse.gif\", \"subtitle\": \"100% Cotton, 200% Comfortable\", \"default_action\": { \"type\": \"web_url\", \"url\": \"https://www.sella.it/banca-online/privati/index.jsp\", \"messenger_extensions\": true, \"webview_height_ratio\": \"tall\", \"fallback_url\": \"https://www.sella.it/banca-online/privati/index.jsp\" }, \"buttons\": [ { \"title\": \"Shop Now\", \"type\": \"web_url\", \"url\": \"https://udayanbot.herokuapp.com/img/hype_bse.gif\", \"messenger_extensions\": true, \"webview_height_ratio\": \"tall\", \"fallback_url\": \"https://www.sella.it/banca-online/privati/index.jsp\" } ] }, { \"title\": \"Classic Blue T-Shirt\", \"image_url\": \"https://udayanbot.herokuapp.com/img/hype_bse.gif\", \"subtitle\": \"100% Cotton, 200% Comfortable\", \"default_action\": { \"type\": \"web_url\", \"url\": \"https://www.sella.it/banca-online/privati/index.jsp\", \"messenger_extensions\": true, \"webview_height_ratio\": \"tall\", \"fallback_url\": \"https://www.sella.it/banca-online/privati/index.jsp\" }, \"buttons\": [ { \"title\": \"Shop Now\", \"type\": \"web_url\", \"url\": \"https://udayanbot.herokuapp.com/img/hype_bse.gif\", \"messenger_extensions\": true, \"webview_height_ratio\": \"tall\", \"fallback_url\": \"https://www.sella.it/banca-online/privati/index.jsp\" } ] } ], \"buttons\": [ { \"title\": \"View More\", \"type\": \"postback\", \"payload\": \"payload\" } ] } } } }";
		break;
		default:
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"button\", \"text\":\"Non sono riuscita a comprendere la richiesta, se lo desideri ti posso mettere in contatto con un nostro operatore della assitenza.\", \"buttons\":[ { \"type\":\"phone_number\", \"title\":\"Clicca qui per proseguire\", \"payload\":\"+390152434600\" } ] } } } }";
			break; 
		}
		jsonResponse = jsonResponse.replace("recipientId", senderId);
		logger.info("jsonresponse>>>>{}", jsonResponse);
		byte[] utf8Bytes;
		try {
			utf8Bytes = jsonResponse.getBytes("UTF8");
			//return new String(utf8Bytes,"UTF8").replace("è", "e");
			return new String(utf8Bytes,"UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jsonResponse;
		
	}
	
	private static String getActualKeyword(String textSearch) {
		String searchString=textSearch;
		if(textSearch.equals("first hand shake")) {
			searchString="welcome msg";
		}
		else if( textSearch.contains("product")) {
			searchString="view product";
		}else if(textSearch.contains("conti") || textSearch.contains("account") || textSearch.contains("canti")) {
			searchString="list conti";
		}else if((textSearch.contains("carte") || textSearch.contains("card")|| textSearch.contains("carti")) && !(textSearch.contains("debit")||textSearch.contains("credit") || textSearch.contains("all carte details") || textSearch.contains("master"))) {
			searchString="list carte";
		}else if(textSearch.contains("websella") || textSearch.contains("websela")) {
			searchString="websella";
		}else if(textSearch.contains("debit")) {
			searchString="carte di debito";
		}else if(textSearch.contains("credit")) {
			searchString="carte di credito";
		}else if(textSearch.contains("hype")) {
			searchString="hype plus";
		}else if(textSearch.contains("visa")) {
			searchString="visa classic";
		}else if(textSearch.contains("master")) {
			searchString="mastercard classic";
		}else if(textSearch.contains("invest")) {
			searchString="list investimenti";
		}else if(textSearch.contains("finan")) {
			searchString="list finanziamenti";
		}else if(textSearch.contains("protezione")) {
			searchString="list protezione";
		}else if(textSearch.contains("persona")) {
			searchString="persona";
		}else if(textSearch.contains("famiglia")) {
			searchString="famiglia";
		}else if(textSearch.contains("auto")) {
			searchString="auto";
		}else if(textSearch.contains("casa")) {
			searchString="casa";
		}else if(textSearch.contains("sella.it") || textSearch.contains("prestito sella")) {
			searchString="prestito sella.it";
		}else if(textSearch.contains("mutui")) {
			searchString="mutui";
		}else if(textSearch.contains("prestito d’onore") || textSearch.contains("prestito donore")||textSearch.contains("donore")) {
			searchString="prestito d’onore";
		}else if(textSearch.contains("cessione del quinto") || textSearch.contains("cessione")||textSearch.contains("quinto")) {
			searchString="cessione del quinto";
		}else if(textSearch.contains("ambiente") || textSearch.contains("prestidea")) {
			searchString="prestidea ambiente";
		}else if(textSearch.contains("morning") || textSearch.contains("noon")|| textSearch.contains("evening")||textSearch.contains("ciao")||textSearch.contains("caio")||textSearch.contains("ciao") || textSearch.contains("hi")|| textSearch.contains("hai") || textSearch.contains("hello") || textSearch.contains("helo")) {
			searchString="hi";
		}else if(textSearch.contains("thank") || textSearch.contains("bye")|| textSearch.contains("grazie")) {
			searchString="bye";
		}
		else if(textSearch.contains("consulenza")) {
			searchString="consulenza";
		}
		else if(textSearch.contains("evolution")) {
			searchString="evolution";
		}
		else if(textSearch.contains("fondi")) {
			searchString="fondi";
		}
		else if(textSearch.contains("pir")) {
			searchString="pir";
		}
		logger.info("The actual Search String  is{}",searchString);
		return searchString;
		
	}
}





