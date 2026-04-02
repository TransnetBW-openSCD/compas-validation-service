<?xml version="1.0" encoding="UTF-8"?>
<SCL xmlns="http://www.iec.ch/61850/2003/SCL"
     version="2007"
     revision="B">

  <Header id="SCD1" version="1" revision="0" toolID="EngineeringTool"/>

  <Substation name="SS1">
    <VoltageLevel name="VL1" nomFreq="50">
      <Bay name="Bay1"/>
    </VoltageLevel>
  </Substation>

  <Communication>
    <SubNetwork name="StationBus" type="8-MMS">
      <ConnectedAP iedName="IED1" apName="AP1">
        <Address>
          <P type="IP">192.168.1.10</P>
          <P type="OSI-AP-Title">1,3,9999,23</P>
        </Address>
      </ConnectedAP>
    </SubNetwork>
  </Communication>

  <IED name="IED1" manufacturer="ACME" type="ProtectionRelay">
    <AccessPoint name="AP1">
      <Server>
        <LDevice inst="LD_PROT">

          <LN0 lnClass="LLN0" inst="">
            <DataSet name="dsEvents">
              <FCDA ldInst="LD_PROT" lnClass="PTOC" lnInst="1" doName="Str" fc="ST"/>
            </DataSet>

            <ReportControl name="rcEvents"
                           datSet="dsEvents"
                           rptID="IED1/LLN0$Events"
                           confRev="1"
                           buffered="true"
                           intgPd="1000"/>
          </LN0>

          <LN lnClass="PTOC" inst="1" lnType="PTOC_Type"/>

        </LDevice>
      </Server>
    </AccessPoint>
  </IED>

</SCL>
